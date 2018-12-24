package spring.cloud.learn.micro.service.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    private static final Logger iotLogger =
            LogManager.getLogger(ValuesController.class);

    @Autowired
    Tracer tracer;

    private static final Map<String,String> valueMaps = new HashMap<String, String>(){{
        put("test","hello world");
    }};

    @GetMapping("/values/{key}")
    public String getValue(@PathVariable("key") String key){
        System.out.println("trace:\t" + tracer.getCurrentSpan());
        iotLogger.info(key);

        if(key == null || key.equals("") || !valueMaps.containsKey(key)){
            return "value is not exists";
        }
        return valueMaps.get(key);
    }

    @GetMapping("/test/{test}")
    public String getTest(@PathVariable("test") String test) throws InterruptedException {
        System.out.println("trace:\t" + tracer.getCurrentSpan());
        iotLogger.info(test);

        if(test == null || test.equals("") || !valueMaps.containsKey(test)){
            return "value is not exists";
        }
        Thread.sleep(1000);
        return valueMaps.get(test);
    }

    @PutMapping("/values/{key}")
    public String setValue(@PathVariable("key") String key,
                           @RequestParam("value") String value){
        if(key == null || key.equals("") ){
            return "value is not exists";
        }

        valueMaps.putIfAbsent(key,value);

        return "Success";
    }

    @GetMapping("/test/fail")
    public ResponseEntity<?> getFailed(){
        Map<String,Object> errorResult =new  HashMap<>();
        errorResult.put("errorCode",400.2);
        errorResult.put("errorMessage","用户名请求错误");
        return new ResponseEntity<Object>(errorResult,HttpStatus.BAD_REQUEST);
    }
}
