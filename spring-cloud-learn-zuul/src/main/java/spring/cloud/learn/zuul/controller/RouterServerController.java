package spring.cloud.learn.zuul.controller;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author flsh
 * @version 1.0
 * @description
 * @date 2018/10/16
 * @since Jdk 1.8
 */
@RestController
public class RouterServerController {

    private Logger logger = Logger.getLogger("RouterServerController");

    @Autowired
    private SpringClientFactory factory;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/server/{appname}")
    public ResponseEntity<?> showServerList(@PathVariable("appname") String appName){

        ILoadBalancer lb = factory.getLoadBalancer(appName);
        return  ResponseEntity.ok(lb.getReachableServers());
    }

    @GetMapping("/server/cn/{appname}")
    public ResponseEntity<?> getRibbonClientName(@PathVariable("appname") String appName){
        IClientConfig clientConfig = factory.getClientConfig(appName);
        return ResponseEntity.ok(clientConfig.getClientName());
    }

    @PutMapping("/server/{appname}/rest")
    public ResponseEntity<?> restServerList(@PathVariable("appname") String appName){
        ILoadBalancer lb = factory.getLoadBalancer(appName);
        DynamicServerListLoadBalancer dslb = (DynamicServerListLoadBalancer)lb;
       List<Server> updateServerList = dslb.getServerListImpl().getUpdatedListOfServers();
       return ResponseEntity.ok(updateServerList);

    }

    @GetMapping("/server/ins/{appname}")
    public void showInstance(@PathVariable("appname") String appName){

        ServiceInstance serviceInstance = this.loadBalancerClient.choose(appName);
        // 打印当前选择的是哪个节点
        logger.info(String.format("{%s}:{%s}:{%s}", serviceInstance.getServiceId(), serviceInstance.getHost(),
                serviceInstance.getPort()));
    }
}
