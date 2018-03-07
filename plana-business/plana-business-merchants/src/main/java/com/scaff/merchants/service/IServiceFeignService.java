package com.scaff.merchants.service;

import com.scaff.common.web.result.JSONResult;
import com.scaff.model.services.Services;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xyl on 12/26/17.
 */
@FeignClient(name = "PLANA-BUSINESS-SERVICES",path = "/services")
public interface IServiceFeignService {
    @RequestMapping("/{serviceUUID}")
    Services getServices(@RequestParam("serviceUUID") String serviceUUID);
    @RequestMapping("/name/{name}")
    JSONResult getServicesByName(@RequestParam("name") String name);
}
