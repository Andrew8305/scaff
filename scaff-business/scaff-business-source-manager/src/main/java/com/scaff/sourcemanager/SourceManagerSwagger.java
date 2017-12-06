package com.scaff.sourcemanager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by xyl on 11/14/17.
 * 配置swagger信息,该配置类需要在Application同级目录下创建，在项目启动的时候，就初始化该配置类
 */
@EnableSwagger2
@Configuration
public class SourceManagerSwagger {

    @Bean
    public Docket createRestApi() {// 创建API基本信息
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.scaff.sourcemanager"))// 扫描该包下的所有需要在Swagger中展示的API，@ApiIgnore注解标注的除外
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {// 创建API的基本信息，这些信息会在Swagger UI中进行显示
        return new ApiInfoBuilder()
                .title("sourcemanager 项目 接口列表")// API 标题
                .description("sourcemanager 项目 接口列表")// API描述
                .contact(new Contact("薛云龙","","xueyunlong@cafintech.com"))// 联系人
                .version("1.0")// 版本号
                .build();
    }
}
