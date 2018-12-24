package spring.cloud.learn.zuul.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author flsh
 * @version 1.0
 * @description
 * @date 2018/12/21
 * @since Jdk 1.8
 */
@RestController
public class ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
    /**
     * {
     "exception": {
     "cause": {
     "cause": null,
     "stackTrace": [...],
     "errorCode": 0,
     "message": "Load balancer does not have available server for client: micro-service-v1",
     "errorObject": null,
     "errorType": "GENERAL",
     "errorMessage": "Load balancer does not have available server for client: micro-service-v1",
     "internalMessage": "{no message: 0}",
     "localizedMessage": "Load balancer does not have available server for client: micro-service-v1",
     "suppressed": []
     },
     "stackTrace": [...],
     "nStatusCode": 500,
     "errorCause": "GENERAL",
     "localizedMessage": "Forwarding error",
     "message": "Forwarding error",
     "suppressed": []
     },
     "exception.nStatusCode": 500,
     "exception.errorCause": "GENERAL"
     }
     * @param request
     * @return
     */
    @GetMapping(value = "/error")
    public ResponseEntity<?> error(HttpServletRequest request){
        //       Enumeration<String> attrNames = request.getAttributeNames();
//       Map<String,Object> retval = new HashMap<>();
//       while (attrNames.hasMoreElements()){
//               String name = attrNames.nextElement();
//               retval.put(name, request.getAttribute(name));
//       }
        Exception error = (Exception)request.getAttribute("javax.servlet.error.exception");
        //格式化返回结果
        Map<String,Object> retval = new HashMap<>();

//        retval.put("exception.nStatusCode",request.getAttribute("javax.servlet.error.status_code"));
        retval.put("result",3);
        retval.put("errorCode",request.getAttribute("javax.servlet.error.status_code"));
        retval.put("errorMessage", error.getCause().getMessage());
        retval.put("returnValue",null);
//        retval.put("exception.errorCause",request.getAttribute("javax.servlet.error.message"));
        //记录异常到日志
        logger.error("服务出现异常",error);
        return ResponseEntity.ok(retval);
    }

    @GetMapping(value = "/format")
    public ResponseEntity<?> respFormat(HttpServletRequest request, HttpServletResponse response){

        Object body = request.getAttribute("body");
        if(body != null){

        }
        Map<String,Object> retval = new HashMap<>();
        retval.put("result",3);
        retval.put("errorCode",null);
        retval.put("errorMessage", null);
        retval.put("returnValue",body);
        return ResponseEntity.ok(retval);
    }
}
