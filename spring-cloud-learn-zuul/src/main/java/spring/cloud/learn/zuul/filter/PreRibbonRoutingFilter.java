package spring.cloud.learn.zuul.filter;

import com.iemylife.iot.ribbon.context.RibbonFilterContextHolder;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author flsh
 * @version 1.0
 * @description
 *
 * @date 2018/12/3
 * @since Jdk 1.8
 */
public class PreRibbonRoutingFilter extends ZuulFilter {

    private static final Log logger = LogFactory.getLog(PreRibbonRoutingFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.RIBBON_ROUTING_FILTER_ORDER - 1;     //在ribbon filter之前执行
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return (ctx.getRouteHost() == null && ctx.get(FilterConstants.SERVICE_ID_KEY) != null
                && ctx.sendZuulResponse());
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String version = request.getHeader("version");
        if(version != null){
            RibbonFilterContextHolder.getCurrentContext().add("version",version);
            logger.info("version="+version);
        }else{
            RibbonFilterContextHolder.clearCurrentContext();
        }
        return null;
    }
}
