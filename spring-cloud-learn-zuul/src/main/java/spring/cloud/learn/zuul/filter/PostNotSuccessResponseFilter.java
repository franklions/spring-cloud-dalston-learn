package spring.cloud.learn.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

/**
 * @author flsh
 * @version 1.0
 * @description
 * 非200的返回结果过滤器,对于非200的结果转到格式化的Controller去处理。
 * @date 2018/12/21
 * @since Jdk 1.8
 */
public class PostNotSuccessResponseFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(PostNotSuccessResponseFilter.class);

    private static final String POST_NOTSUCCESS_RESPONSE_FILTER_RAN ="postNotSuccessRespfilter.ran";
    /**
     * 处理返回结果的地址
     */
    @Value("${resp.format.path:/format}")
    private String convertPath;


    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 100;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        return context.getThrowable() == null
                && !context.getBoolean(POST_NOTSUCCESS_RESPONSE_FILTER_RAN, false)
        &&(!context.getZuulResponseHeaders().isEmpty()
                || context.getResponseDataStream() != null
                || context.getResponseBody() != null)
                && context.getResponse().getStatus() != HttpStatus.OK.value();
    }

    @Override
    public Object run() {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            HttpServletRequest request = context.getRequest();
            if(context.getResponseBody() != null) {
                request.setAttribute("body", context.getResponseBody());
            }
            InputStream is = null;
            is = context.getResponseDataStream();
            if(is != null){
                String str = IOUtils.toString(is, "utf-8");
                request.setAttribute("body", str);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    this.convertPath);
            if (dispatcher != null) {
                context.set(POST_NOTSUCCESS_RESPONSE_FILTER_RAN, true);
                if (!context.getResponse().isCommitted()) {
                    dispatcher.forward(request, context.getResponse());
                }
            }
        }catch (Exception ex){
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }
}
