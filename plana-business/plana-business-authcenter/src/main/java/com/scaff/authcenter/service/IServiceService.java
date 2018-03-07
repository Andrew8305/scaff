package com.scaff.authcenter.service;

import com.scaff.common.web.result.JSONResult;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by xyl on 12/27/17.
 */
@FeignClient(value = "plana-business-services", path = "/services")
public interface IServiceService {
    @RequestMapping(value = "/name/multiple")
    JSONResult getServicesInfoByNames(@RequestBody List<String> serviceNames);
    @RequestMapping(value = "/name/{serviceName}")
    JSONResult getServiceInfoByName(@PathVariable("serviceName") String serviceName);
}
