package com.scaff.data.auth.service;

import com.scaff.common.web.result.JSONResult;
import com.scaff.data.auth.UpdateUrlInfoReqDTO;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xyl on 1/8/18.
 */
@FeignClient("scaff-oauth2-server")
public interface AuthManagerService {
    @RequestMapping(value = "/updateUrlInfo",method = RequestMethod.POST)
    JSONResult updateUrlInfo(@RequestBody UpdateUrlInfoReqDTO updateUrlInfoReqDTO);
}
