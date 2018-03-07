package com.scaff.oauth2.plana;

import com.alibaba.fastjson.JSONObject;
import com.scaff.common.web.argsresolver.FastJson;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by xyl on 1/8/18.
 */
@RestController
@Slf4j
public class AuthCenterController {
    @PostMapping("/updateUrlInfo")
    public void updateUrlInfo(@FastJson UpdateUrlInfoReqDTO updateUrlInfoReqDTO){
        log.info(JSONObject.toJSONString(updateUrlInfoReqDTO));

    }
}
