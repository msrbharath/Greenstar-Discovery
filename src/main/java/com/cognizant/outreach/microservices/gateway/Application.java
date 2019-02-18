/**
 * ${Application}
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
package com.cognizant.outreach.microservices.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.cognizant.outreach.microservices.gateway.filter.PreFilter;

/**
 * Spring boot application for gate way service
 * 
 * @author 371793
 */
@SpringBootApplication
@EnableZuulProxy
public class Application {

	/**
	 * Boot main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * Implements the Zuul prefilter
	 * 
	 * @return prefilter instance
	 */
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
}
