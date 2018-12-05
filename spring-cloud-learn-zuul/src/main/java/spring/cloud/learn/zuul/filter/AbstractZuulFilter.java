package spring.cloud.learn.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @author flsh
 * @version 1.0
 * @description
 * @date 2018/12/3
 * @since Jdk 1.8
 */
public  abstract class AbstractZuulFilter extends ZuulFilter {

    private static final String NEXT_FILTER = "nextFilter";
    protected RequestContext context;

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return (boolean)(ctx.getOrDefault(NEXT_FILTER,true));
    }

    @Override
    public Object run() {
        context = RequestContext.getCurrentContext();
        return doRun();
    }

    public abstract Object doRun();

    public Object fail(Integer code,String message){
        context.set(NEXT_FILTER,false);
        context.setSendZuulResponse(false);
        context.getResponse().setContentType("text/html;charset=UTF-8");
        context.setResponseStatusCode(code);
        context.setResponseBody(String.format("{\"result\":\"%s\"}",message));
        return null;
    }

    public Object success(){
        context.set(NEXT_FILTER,true);
        return null;
    }
}
