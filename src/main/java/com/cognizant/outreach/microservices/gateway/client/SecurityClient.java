/**
 * ${SecurityClient}
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
package com.cognizant.outreach.microservices.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * To consume security service
 * 
 * @author 371793
 */
@FeignClient("security-service")
public interface SecurityClient {

	/**
	 * To validate the provided api token is valid or not
	 * 
	 * @param user
	 * @return  HttpStatus.ACCEPTED if valid and HttpStatus.UNAUTHORIZED for not a valid token
	 */
    @RequestMapping(method = RequestMethod.POST, path="/security/validatetoken")
    public ResponseEntity<String> validateAPIToken(@RequestBody User user);
  
}
