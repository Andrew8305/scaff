package com.scaff.sourcemanager.controller.upload.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.scaff.model.User;
import com.scaff.sourcemanager.controller.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by xyl on 11/10/17.
 */
@Service@Slf4j
public class UploadRibbonServiceImpl implements UploadService {

    private final RestTemplate restTemplate;

    @Autowired
    public UploadRibbonServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @HystrixCommand(fallbackMethod = "dataAnalyzeFallBack")
    public boolean dataAnalyze(User user) {
        return restTemplate.postForObject("http://BUSINESS-DATA-ANALYZE/analyze",user,boolean.class,new HashMap<String,String>());
    }

    public boolean dataAnalyzeFallBack(User user) {
        log.info("进入 dataAnalyzeFallBack :{}",user.toString());
        return false;
    }
}
