package spring.cloud.learn.zuul;

import com.netflix.client.config.IClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author flsh
 * @version 1.0
 * @description
 * @date 2018/4/2
 * @since Jdk 1.8
 */
@EnableZuulProxy
@SpringCloudApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,
value = {spring.cloud.learn.zuul.config.ExcludeComponentScan.class}))
public class ZuulApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class,args);
    }

//    @Autowired
//    IClientConfig ribbonClientConfig;

    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper(
                "(?<name>^.+)-(?<version>v\\d+$)",
                "${version}/${name}");
    }

    @Override
    public void run(String... strings) throws Exception {
//        System.out.println(ribbonClientConfig.getClientName());
    }
}
