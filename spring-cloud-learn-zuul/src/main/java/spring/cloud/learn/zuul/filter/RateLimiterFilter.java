package spring.cloud.learn.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author flsh
 * @version 1.0
 * @description
 * @date 2018/12/3
 * @since Jdk 1.8
 */
public class RateLimiterFilter extends AbstractZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(RateLimiterFilter.class);
    private static final int RATE_LIMITER_ORDER = 4;    //执行于PreDecorationFilter之前

    /**
     * 每秒处理50个请求
     */
    RateLimiter rateLimiter = RateLimiter.create(50);

    @Override
    public Object doRun() {
        HttpServletRequest request = context.getRequest();
        String url = request.getRequestURI();
        if(rateLimiter.tryAcquire()){
            return success();
        }else{
            logger.info("rate limit:{%s}",url);
            return fail(401,String.format("rate limit:{%s}",url));
        }
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return RATE_LIMITER_ORDER;
    }
}
