package spring.cloud.learn.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author flsh
 * @version 1.0
 * @description
 * @date 2018/4/3
 * @since Jdk 1.8
 */
@EnableEurekaClient
@SpringBootApplication
public class ProxyApplication {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class,args);
    }
}
