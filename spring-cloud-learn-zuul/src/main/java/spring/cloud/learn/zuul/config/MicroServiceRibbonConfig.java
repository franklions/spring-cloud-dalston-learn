package spring.cloud.learn.zuul.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import com.netflix.niws.loadbalancer.EurekaNotificationServerListUpdater;
import org.springframework.cloud.netflix.ribbon.ZonePreferenceServerListFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author flsh
 * @version 1.0
 * @description
 * @date 2018/11/9
 * @since Jdk 1.8
 */
//@Configuration
//@ExcludeComponentScan
public class MicroServiceRibbonConfig {

    @Bean
    public IRule ribbonRule() {
        // 负载均衡规则改为随机
        return new RandomRule();
    }

    @Bean
    public ServerListUpdater ribbonServerListUpdater(IClientConfig clientConfig) {
        return new EurekaNotificationServerListUpdater();
    }

    @Bean
    public ServerListFilter<Server> ribbonServerListFilter() {
        ZonePreferenceServerListFilter filter = new ZonePreferenceServerListFilter();
        return filter;
    }
}
