/**
 * ${PreFilter}
 *
 *  2019 Cognizant Technology Solutions. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of Cognizant Technology
 *  Solutions("Confidential Information").  You shall not disclose or use Confidential
 *  Information without the express written agreement of Cognizant Technology Solutions.
 *  Modification Log:
 *  -----------------
 *  Date                   Author           Description
 *  18/Feb/2019            371793        Developed base code structure
 *  ---------------------------------------------------------------------------
 */
package com.cognizant.outreach.microservices.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.cognizant.outreach.microservices.gateway.client.SecurityClient;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * Zuul pre filter authentication to check security
 * 
 * @author 371793
 */
public class PreFilter extends ZuulFilter {

protected Logger logger = LoggerFactory.getLogger(ZuulFilter.class);
	
 @Autowired
 private SecurityClient securityClient;

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
	RequestContext ctx = RequestContext.getCurrentContext();
	String requestURL = ctx.getRequest().getRequestURL().toString();
	if(requestURL.toLowerCase().contains("/api/auth")) {
		 logger.info("Authentication required for this request ==> {}",requestURL);
		 return true;
	}
    return false;
  }

  @Override
  public Object run(){
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    String apiToken = request.getHeader("apitoken");
    String userId = request.getHeader("userid");
    // If API token is empty or expired then stop proceeding and return unauthorized
    if(!StringUtils.isEmpty(apiToken) || 
    		!securityClient.validateAPIToken(apiToken, userId).getStatusCode().equals(HttpStatus.ACCEPTED)) {
    	ctx.setSendZuulResponse(false);
    	ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
    	ctx.setResponseBody("Invalid or expired API token");
    	logger.debug("API token is expired or invalid token ==> {} ",apiToken);
    }
    return null;
  }

}