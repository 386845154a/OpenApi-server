package com.github.hollykunge.openapi.security.jwt;

import cn.hutool.core.util.StrUtil;
import com.github.hollykunge.openapi.biz.UserBiz;
import com.github.hollykunge.openapi.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
     //private static final String secret ="this is a secret";
    private static final Long expiration=1800L;
    private static final String tokenHead="Bearer ";
    private static final String CLAIM_KEY_ROLES = "roles";

    @Autowired
    UserBiz userBiz;
    @Autowired
    JwtProperties jwtProperties;

    //根据用户信息生成token
    /*public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }*/
    // 根据权限生成JWT的token
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }
    // token中解出用户名
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }
    //token中解出claims
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    public Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }


    // 根据用户信息生成token
    public Map<String, String> generateToken(UserDetails userDetails) {
        Map<String, String> rst = new HashMap<String, String>();
        // 访问token
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        rst.put(getAccessTokenKey(), generateToken(claims, jwtProperties.getAccessExpiration()));

        rst.put(getRefreshTokenKey(), generateToken(claims, jwtProperties.getRefreshExpiration()));

        claims.put(CLAIM_KEY_ROLES, userDetails.getAuthorities());
        rst.put(getRoleTokenKey(), generateToken(claims, jwtProperties.getRolesExpiration()));

        return rst;
    }

    // 根据权限生成JWT的token
    private String generateToken(Map<String, Object> claims, Integer seconds) {
        return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate(seconds))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret()).compact();
    }
    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate(Integer seconds) {
        return new Date(System.currentTimeMillis() + (int) (seconds * 1000));
    }
    //根据token获得roles
    public List<GrantedAuthority> getRolesFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        List<HashMap> roles =  (List<HashMap>) claims.get(CLAIM_KEY_ROLES);
        if(roles == null || roles.isEmpty()){
            return null;
        }
        List<GrantedAuthority> authority = roles.stream().map(i->new SimpleGrantedAuthority((String) i.get("authority"))).collect(Collectors.toList());
        return authority;
    }
    //几个key及生成方式
    public String getAccessTokenKey(){
        return "accesstoken";
    }
    public String getAccessTokenKey(String username){
        return username+":accesstoken";
    }
    public String getRefreshTokenKey(){
        return "refreshtoken";
    }
    public String getRefreshTokenKey(String username){
        return username+":refreshtoken";
    }
    public String getRoleTokenKey(){
        return "roletoken";
    }
    public String getRoleTokenKey(String username){
        return username+":roletoken";
    }
    public String refreshHeadToken(String refreshtoken,String accesstoken) {
        if (StrUtil.isEmpty(refreshtoken)) {
            return null;
        }
        String username = getUserNameFromToken(refreshtoken);
        if (StrUtil.isEmpty(username)) {
            return null;
        }
        // 如果token在30分钟之内刚刷新过，返回原token
        if (accesstoken != null && tokenRefreshJustBefore(accesstoken, 30 * 60)) {
            return "";
        } else {
            Map<String, Object> accessClaims = new HashMap<>();
            accessClaims.put(CLAIM_KEY_USERNAME,username);
            accessClaims.put(CLAIM_KEY_CREATED, new Date());
            return generateToken(accessClaims, jwtProperties.getAccessExpiration());
        }
    }

    private boolean tokenRefreshJustBefore(String accesstoken, int seccondes){
        if(isTokenExpired(accesstoken)){
            return false;
        }
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(accesstoken)
                .getBody();
        if(claims == null){
            return false;
        }
        long createLong = (Long)claims.get(CLAIM_KEY_CREATED);
        long now = System.currentTimeMillis();
        //剩下五分钟可以刷新
        return  (seccondes - (int)(now - createLong)/1000) > 300;
    }

}