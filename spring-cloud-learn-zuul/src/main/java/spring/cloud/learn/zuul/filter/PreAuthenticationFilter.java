package spring.cloud.learn.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;

/**
 * @author flsh
 * @version 1.0
 * @description
 * 验证权限 filter,所有未验证权限之前的请求都需要进行权限验证
 * @date 2018/12/20
 * @since Jdk 1.8
 */
public class PreAuthenticationFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(PreAuthenticationFilter.class);
    /**
     * 执行于PreDecorationFilter之前
     */
    private static final int AUTH_FILTER_ORDER = 4;

    /**
     * 在请求上下文中表示已经通过权限验证的请求
     */
    private static final String AUTH_FILTER_CONTENT="authFilter";


    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return AUTH_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return !(boolean)ctx.getOrDefault(AUTH_FILTER_CONTENT,false);
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String uid = ctx.getRequest().getParameter("uid");
        String pwd = ctx.getRequest().getParameter("pwd");
        if(uid== null || pwd == null || !(uid.equals("admin") && pwd.equals("123456")) ){
            //不再路由
             ctx.setSendZuulResponse(false);
             ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
             ctx.setResponseBody("not auth");
             return null;
        }

        //已经通过权限验证
        ctx.set(AUTH_FILTER_CONTENT,true);
        return null;
    }
}
