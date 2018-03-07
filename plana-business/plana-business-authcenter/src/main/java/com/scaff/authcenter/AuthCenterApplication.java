package com.scaff.authcenter;


import com.scaff.common.web.AbstractWebApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by xyl on 11/10/17.
 */
@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableFeignClients
public class AuthCenterApplication extends AbstractWebApplication {
    public static void main(String[] args) {

        context = SpringApplication.run(AuthCenterApplication.class, args);
        for (String name : context.getBeanDefinitionNames()) {
            log.info(name);
        }
    }
}

