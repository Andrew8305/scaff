package com.scaff.api.gateway.service;

import com.scaff.common.web.result.JSONResult;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xyl on 12/27/17.
 */
@FeignClient(value = "plana-business-merchants",path = "/merchants")
public interface IMerchantsService {
    @RequestMapping(value = "/primaryKey/{merchantUUID}")
    JSONResult getMerchantPrimaryKey(@PathVariable("merchantUUID") String merchantUUID);
}
