package spring.cloud.learn.micro.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author flsh
 * @version 1.0
 * @description
 * @date 2018/4/2
 * @since Jdk 1.8
 */
@RestController
public class ValuesController {

    private static final Map<String,String> valueMaps = new HashMap<String, String>(){{
        put("test","hello world");
    }};

    @GetMapping("/values/{key}")
    public String getValue(@PathVariable("key") String key){
        if(key == null || key.equals("") || !valueMaps.containsKey(key)){
            return "value is not exists";
        }
        return valueMaps.get(key);
    }
}
