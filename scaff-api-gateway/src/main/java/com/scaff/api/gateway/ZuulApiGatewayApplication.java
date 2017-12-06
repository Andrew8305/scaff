package com.scaff.api.gateway;

import com.scaff.api.gateway.filter.PreLabelFilter;
import com.scaff.api.gateway.filter.PreRequestLogFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * Created by xyl on 11/2/17.
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulApiGatewayApplication {

    @Bean
    public PreRequestLogFilter preRequestLogFilter() {
        return new PreRequestLogFilter();
    }
    @Bean
    public PreLabelFilter preLabelFilter(){
        return new PreLabelFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZuulApiGatewayApplication.class, args);
    }
}