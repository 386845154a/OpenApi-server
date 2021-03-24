package com.github.hollykunge.openapi.security.config;



import com.github.hollykunge.openapi.security.filter.JwtokenAuthenticationFilter;
import com.github.hollykunge.openapi.security.handler.MyAuthenticationFailureHandler;
import com.github.hollykunge.openapi.security.handler.MyAuthenticationSuccessHandler;
import com.github.hollykunge.openapi.security.provider.MyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private JwtokenAuthenticationFilter jwtokenAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //JWT验证
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        //用户密码验证
        http.csrf().disable();

        http.httpBasic()
            .and()
        .authorizeRequests()
                //测试接口，不需要拦截，开发测试使用
            .antMatchers("/test/**").permitAll()
                //后端服务调用其他服务接口，不需要拦截，由系统拦截器拦截，校验访问接口权限
            .antMatchers("/openApi/**").permitAll()
                //前端登录获取、刷新token不用拦截
            .antMatchers("/appAuth/**").permitAll()
                //第三方后端获取token不需要拦截，第三方携带appid、appSecret换取
            .antMatchers("/auth/getToken").permitAll()
                //注册应用（用户）不用拦截
            .antMatchers("/auth/registerApp").permitAll()
            //.antMatchers("/auth/**").permitAll()
            //.antMatchers("/apply/**").permitAll()
            //.antMatchers("/service/**").permitAll()
            //.antMatchers("/app/**").permitAll()
            .anyRequest().authenticated()
            .and()
       /* .formLogin()
            //登录跳转页面
            .loginPage("/auth/login.html")
            //.usernameParameter("username").passwordParameter("password")  //username和password对应前端表单的name键
             //指定自定义form表单请求的路径
            .loginProcessingUrl("/auth/login")
            .successHandler(myAuthenticationSuccessHandler)
            .failureHandler(myAuthenticationFailureHandler)
            .permitAll()
            .and()*/
        .logout()
            //默认为/logout，不用改
            .logoutUrl("/logout")  
            .logoutSuccessUrl("/auth/logout.html") //默认跳转到 /login
            .deleteCookies("JSESSIONID");   //默认也是会删除cookie的
     }
}