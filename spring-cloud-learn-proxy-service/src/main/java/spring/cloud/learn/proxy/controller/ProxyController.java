package spring.cloud.learn.proxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author flsh
 * @version 1.0
 * @description
 * @date 2018/4/3
 * @since Jdk 1.8
 */
@RestController
public class ProxyController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{key}")
    public String getValues(@PathVariable("key") String key){
        return restTemplate.exchange("http://zuul-server/micro-service/values/"+key,
                HttpMethod.GET,null,String.class).getBody();
    }
}
