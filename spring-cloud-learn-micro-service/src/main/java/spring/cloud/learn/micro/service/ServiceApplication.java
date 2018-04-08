package spring.cloud.learn.micro.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author flsh
 * @version 1.0
 * @description
 * @date 2018/4/2
 * @since Jdk 1.8
 */
@SpringBootApplication
@EnableEurekaClient
public class ServiceApplication {

    @Bean
    public Executor poolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.initialize();
        return executor;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class,args);
    }
}
