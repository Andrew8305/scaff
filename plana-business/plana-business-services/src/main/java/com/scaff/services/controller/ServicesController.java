package com.scaff.services.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Condition;
import com.scaff.common.web.result.JSONResult;
import com.scaff.common.web.result.Result;
import com.scaff.model.services.Services;
import com.scaff.services.service.IServicesService;
import com.scaff.utils.RandomUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 服务信息表 前端控制器
 * </p>
 *
 * @author xueyunlong
 * @since 2017-12-26
 */
@RestController
@RequestMapping("/services")
public class ServicesController {
    @Autowired
    IServicesService iServicesService;

    @PostMapping("/add")
    public void addService(){
        Services services = Services.builder()
                .serviceUuid(RandomUtil.generateUUIDWithTime())
                .serviceName("service-reg")
                .servicePrice(100L)
                .build();
        iServicesService.insert(services);
    }

    @GetMapping("/{serviceUUID}")
    public JSONResult getServiceInfo(@PathVariable("serviceUUID") String serviceUUID){
        return JSONResult.ok(
                iServicesService.selectOne(Condition.create().eq("serviceUuid",serviceUUID))
        );
    }
    @GetMapping("/multiple")
    public JSONResult getServicesInfo(@RequestBody List<String> serviceUUIDs){

        return JSONResult.ok(
                iServicesService.selectList(Condition.create().in("serviceName",serviceUUIDs))
        );
    }
    @GetMapping("/name/{serviceName}")
    public JSONResult getServiceInfoByName(@PathVariable("serviceName") String serviceName){
        return JSONResult.ok(
                iServicesService.selectOne(Condition.create().eq("serviceName",serviceName))
        );
    }
    @GetMapping("/name/multiple")
    public JSONResult getServicesInfoByName(@RequestBody List<String> serviceNames){
        return JSONResult.ok(
                iServicesService.selectOne(Condition.create().in("serviceName",serviceNames))
        );
    }

}

