package com.scaff.authcenter.controller;

import com.alibaba.fastjson.JSONObject;
import com.scaff.model.authcenter.dto.AuthServicesReqDTO;
import com.scaff.authcenter.service.IMerchantService;
import com.scaff.authcenter.service.IServiceService;
import com.scaff.common.web.argsresolver.FastJson;
import com.scaff.common.web.result.JSONResult;
import com.scaff.common.web.result.Result;
import com.scaff.model.authcenter.AuthResult;
import com.scaff.model.merchants.dto.GetMerchantResDTO;
import com.scaff.model.merchants.dto.UpdateBalanceReqDTO;
import com.scaff.model.services.Services;
import com.scaff.utils.exception.WebBusinessCodeEnum;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by xyl on 12/27/17.
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthCenterController {

    @Resource
    private IMerchantService iMerchantService;

    @Resource
    private IServiceService iServiceService;

    @PostMapping(value = "/services")
    public JSONResult authServices(@FastJson AuthServicesReqDTO authServicesReqDTO){


        JSONResult merchantResult = iMerchantService.getMerchants(authServicesReqDTO.getMerchantUUID());
        if (!merchantResult.isSuccess()){
            return merchantResult;
        }
        GetMerchantResDTO merchantResDTO =  merchantResult.get(GetMerchantResDTO.class);

        JSONResult result = iServiceService.getServiceInfoByName(authServicesReqDTO.getServiceName());
        if (!result.isSuccess()){
            return result;
        }
        Services services =  result.get(Services.class);

        log.info("该商户服务信息 ：{}", JSONObject.toJSONString(merchantResDTO));
        AuthResult authResult;
        long accountBalance = merchantResDTO.getMerchantAccount().getAccountBalance();
        if (accountBalance < services.getServicePrice()){
            return JSONResult.error(WebBusinessCodeEnum.INSUFFICIENT_BALANCE);
        }else if (!merchantResDTO.getMerchantServicesConfig().getAuthServices().contains(services.getServiceName())){
            return JSONResult.error(WebBusinessCodeEnum.SERVICE_NOT_AUTH);
        }else {
            authResult = AuthResult.builder()
                            .serviceName(services.getServiceName())
                            .success(true)
                            .tip(WebBusinessCodeEnum.SERVICE_AUTH_SUCCESS.getMessage())
                            .build();
            //更新商户余额
            iMerchantService.updateBalance(
                    UpdateBalanceReqDTO.builder()
                            .merchantUUID(authServicesReqDTO.getMerchantUUID())
                            .amount(-services.getServicePrice())
                            .build()
            );

        }
        return JSONResult.ok(authResult);

    }
}
