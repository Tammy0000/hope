package org.isandy.hope.Config.Async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class ThreadFactoryConfig {
    @Bean
    public Executor threadFactory() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setThreadFactory(r -> Thread.ofVirtual().factory().newThread(r));
        executor.setThreadNamePrefix("Hope-");
        executor.initialize();
        return executor;
    }
}
