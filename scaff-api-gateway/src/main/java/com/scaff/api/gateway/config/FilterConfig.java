package com.scaff.api.gateway.config;
import com.scaff.api.gateway.filter.PreLabelFilter;
import com.scaff.api.gateway.filter.PreRequestLogFilter;
import com.scaff.api.gateway.security.HeaderEnhanceFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xyl on 12/13/17.
 */
@Configuration
@EnableAutoConfiguration
public class FilterConfig {

    @Autowired
    HeaderEnhanceFilter headerEnhanceFilter;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(headerEnhanceFilter);
        registrationBean.setOrder(0);
        return registrationBean;
    }

    @Bean
    public PreRequestLogFilter preRequestLogFilter() {
        return new PreRequestLogFilter();
    }

    @Bean
    public PreLabelFilter preLabelFilter(){
        return new PreLabelFilter();
    }

    @Bean
    public HeaderEnhanceFilter headerEnhanceFilter() {
        return new HeaderEnhanceFilter();
    }

}
