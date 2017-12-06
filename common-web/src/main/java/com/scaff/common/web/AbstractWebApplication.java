package com.scaff.common.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.StopWatch;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by xyl on 11/9/17.
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.scaff.common.web"})
@Slf4j
public abstract class AbstractWebApplication extends SpringBootServletInitializer implements ApplicationRunner {

    public void start(ApplicationArguments arguments) {
    }

    protected static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    @Override
    public void run(ApplicationArguments var1) throws Exception{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("---------------------------- {} start running web with args = {} ----------------------------", this.getClass().getName(), var1);

        start(var1);

        stopWatch.stop();
        log.info("---------------------------- end running web  costTime = {} (ms) ----------------------------", stopWatch.getTime());
    }
}
