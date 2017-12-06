package com.scaff.sourcemanager.controller.upload.service;

import com.scaff.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xyl on 11/10/17.
 */
@FeignClient(name = "BUSINESS-DATA-ANALYZE" , fallback = UploadFeignService.UploadFeignServiceFallback.class)
public interface UploadFeignService{

    @RequestMapping("/analyze")
    boolean dataAnalyze(@RequestBody User user);

    @Component@Slf4j
    class UploadFeignServiceFallback implements UploadFeignService{

        @Override
        public boolean dataAnalyze(User user) {
            log.info("进入 UploadFeignServiceFallback");
            return false;
        }
    }

}
