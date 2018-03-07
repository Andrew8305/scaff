package com.scaff.authcenter.service;

import com.scaff.common.web.argsresolver.FastJson;
import com.scaff.common.web.result.JSONResult;
import com.scaff.model.merchants.dto.UpdateBalanceReqDTO;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xyl on 12/27/17.
 */
@FeignClient(value = "plana-business-merchants",path = "/merchants")
public interface IMerchantService {

    @RequestMapping(value = "/services/{merchantUUID}")
    JSONResult getMerchantAuthServices(@RequestParam("merchantUUID") String merchantUUID);

    @RequestMapping(value = "/{merchantUUID}")
    JSONResult getMerchants(@RequestParam("merchantUUID") String merchantUUID);

    @RequestMapping(value = "/updateBalance")
    JSONResult updateBalance(@RequestBody UpdateBalanceReqDTO dto);

}
