package com.github.hollykunge.openapi.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: zhuqz
 * @date: 2020/12/10 16:51
 * @description: 线程池
 */
@Configuration
@EnableAsync
public class ExecutorConfig {
    @Bean
    public Executor asyncServiceExecutor() {
        int corePoolSize = 100,  maxPoolSize = 300, queueNum = 3000;
        String threadPrefix = "apiThreadPool-";

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(corePoolSize);
        //核心线程也关闭
        executor.setAllowCoreThreadTimeOut(true);
        //一分钟关闭空闲线程
        executor.setKeepAliveSeconds(60);
        //配置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        //配置队列大小
        executor.setQueueCapacity(queueNum);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix(threadPrefix);
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }

}
