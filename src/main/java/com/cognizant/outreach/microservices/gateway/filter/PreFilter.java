package com.cognizant.outreach.microservices.gateway.filter;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreFilter extends ZuulFilter {

 protected Logger logger = Logger.getLogger(ZuulFilter.class.getName());
	
  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 1;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    logger.info(String.format("Request content length {}", request.getContentLength()));
    System.out.println("Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());

    return null;
  }

}