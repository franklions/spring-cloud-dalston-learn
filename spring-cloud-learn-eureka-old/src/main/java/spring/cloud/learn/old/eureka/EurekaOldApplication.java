package spring.cloud.learn.old.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author flsh
 * @version 1.0
 * @description
 * @date 2018/4/3
 * @since Jdk 1.8
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaOldApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaOldApplication.class,args);
    }
}
