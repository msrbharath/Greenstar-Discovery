package com.cognizant.outreach.microservices.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("auth-service")
public interface AuthClient {

    @RequestMapping(method = RequestMethod.GET, value = "/auth/validatetoken/{apiToken}")
    String getTokenValidated(@PathVariable("apiToken") String token);
   
}
