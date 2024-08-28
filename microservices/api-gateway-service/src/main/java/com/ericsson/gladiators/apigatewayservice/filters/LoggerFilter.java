package com.ericsson.gladiators.apigatewayservice.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

//Pre filter for logging access to Retrofit Tool APIs
public class LoggerFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(LoggerFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        //request and state information
        RequestContext requestContext = RequestContext.getCurrentContext();

        //gets the http servlet request from the request context
        HttpServletRequest httpServletRequest = requestContext.getRequest();

        logger.info(httpServletRequest.getMethod() + " request to " + httpServletRequest.getRequestURL().toString());

        return null;
    }

}
