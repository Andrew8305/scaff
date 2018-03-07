package com.scaff.api.gateway.config;
import com.scaff.api.gateway.filter.PreLabelFilter;
import com.scaff.api.gateway.filter.PreRequestLogFilter;
import com.scaff.api.gateway.filter.planna.DecryptRequestFilter;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xyl on 12/13/17.
 */
@Configuration
@EnableAutoConfiguration
public class FilterConfig {

    @Bean
    public PreRequestLogFilter preRequestLogFilter() {
        return new PreRequestLogFilter();
    }

    @Bean
    public PreLabelFilter preLabelFilter(){
        return new PreLabelFilter();
    }

    @Bean
    public DecryptRequestFilter decryptRequestFilter() {
        return new DecryptRequestFilter();
    }

}
