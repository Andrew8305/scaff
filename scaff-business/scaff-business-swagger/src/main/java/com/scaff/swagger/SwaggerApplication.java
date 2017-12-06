package com.scaff.swagger;

import com.scaff.common.web.AbstractWebApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xyl on 11/9/17.
 */
@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
@EnableCircuitBreaker
public class SwaggerApplication extends AbstractWebApplication {
    public static void main(String[] args) {

        context = SpringApplication.run(SwaggerApplication.class, args);
        for (String name : context.getBeanDefinitionNames()) {
            log.info(name);
        }
    }
}
