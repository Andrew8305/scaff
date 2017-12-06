package com.scaff.data.analyze;

import com.scaff.common.web.AbstractWebApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by xyl on 11/10/17.
 */
@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
public class AnalyzeApplication extends AbstractWebApplication {
    public static void main(String[] args) {

        context = SpringApplication.run(AnalyzeApplication.class, args);
        for (String name : context.getBeanDefinitionNames()) {
            log.info(name);
        }
    }
}

