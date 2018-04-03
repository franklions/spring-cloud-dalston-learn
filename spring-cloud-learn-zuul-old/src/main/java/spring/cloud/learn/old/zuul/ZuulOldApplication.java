package spring.cloud.learn.old.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author flsh
 * @version 1.0
 * @description
 * @date 2018/4/3
 * @since Jdk 1.8
 */
@SpringCloudApplication
@EnableZuulProxy
public class ZuulOldApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulOldApplication.class,args);
    }
}
