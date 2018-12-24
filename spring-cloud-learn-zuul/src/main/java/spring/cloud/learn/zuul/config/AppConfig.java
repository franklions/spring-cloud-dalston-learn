package spring.cloud.learn.zuul.config;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.cloud.learn.zuul.filter.PostNotSuccessResponseFilter;
import spring.cloud.learn.zuul.filter.PreAuthenticationFilter;
import spring.cloud.learn.zuul.filter.PreRibbonRoutingFilter;
import spring.cloud.learn.zuul.filter.RateLimiterFilter;

/**
 * @author flsh
 * @version 1.0
 * @description
 * @date 2018/11/9
 * @since Jdk 1.8
 */
@Configuration
//@RibbonClient(name="MICRO-SERVICE-V1",configuration = MicroServiceRibbonConfig.class)
public class AppConfig {

    @Bean
    public PreRibbonRoutingFilter preRibbonRoutingFilter(){
        return new PreRibbonRoutingFilter();
    }

    @Bean
    public RateLimiterFilter rateLimiterFilter(){
        return new RateLimiterFilter();
    }

    @Bean
    public PreAuthenticationFilter preAuthenticationFilter(){
        return new PreAuthenticationFilter();
    }

    @Bean
    public PostNotSuccessResponseFilter postNotSuccessResponseFilter(){
        return new PostNotSuccessResponseFilter();
    }
}
