package com.scaff.api.gateway.service;

import com.scaff.api.gateway.model.AuthServicesReqDTO;
import com.scaff.common.web.result.JSONResult;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xyl on 12/27/17.
 */
@FeignClient(value = "PLANA-BUSINESS-AUTHCENTER",path = "/auth")
public interface IAuthCenterService {

    @RequestMapping(value = "/services",method = RequestMethod.POST)
    JSONResult authService(AuthServicesReqDTO authServicesReqDTO);
}
