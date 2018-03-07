package com.scaff.data.auth;

import com.netflix.discovery.EurekaClient;
import com.scaff.data.auth.service.AuthManagerService;
import com.scaff.model.UrlPermission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by xyl on 1/8/18.
 */
@Slf4j
public class PlanaAuthImpl {
    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    AuthManagerService authManagerService;

    @Autowired
    EurekaClient eurekaClient;

    @PostConstruct
    public void prepareUrl() {
      log.info(requestMappingHandlerMapping.toString());

      UpdateUrlInfoReqDTO updateUrlInfoReqDTO = UpdateUrlInfoReqDTO.builder()
              .urlPermissions(
                      requestMappingHandlerMapping.getHandlerMethods().entrySet().stream()
                              .map(requestMappingInfoHandlerMethodEntry -> UrlPermission.builder()
                              .application(eurekaClient.getApplicationInfoManager().getEurekaInstanceConfig().getAppname())
                              .path((String) requestMappingInfoHandlerMethodEntry.getKey().getPatternsCondition().getPatterns().toArray()[0])
                              .method(requestMappingInfoHandlerMethodEntry.getValue().getMethod().toString())
                              .build())
                              .collect(Collectors.toList())
              )
              .build();

      authManagerService.updateUrlInfo(updateUrlInfoReqDTO);
    }
}
