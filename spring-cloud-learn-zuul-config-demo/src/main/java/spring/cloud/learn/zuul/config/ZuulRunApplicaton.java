package spring.cloud.learn.zuul.config;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author flsh
 * @version 1.0
 * @date 2019-04-17
 * @since Jdk 1.8
 */
@SpringCloudApplication
@EnableZuulProxy
public class ZuulRunApplicaton {
    public static void main(String[] args) {
        SpringApplication.run(ZuulRunApplicaton.class,args);
    }
}
