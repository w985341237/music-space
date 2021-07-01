package com.neil.musicspace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description Swagger 配置文件
 * @Author neil
 * @Date 2021/6/29 10:11
 * @Version 1.0
 **/
@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfig implements WebMvcConfigurer {

    //配置swagger的Docket的Bean实例
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.neil.musicspace.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //自定义swagger信息
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Music Space 接口文档")
                .description("Music Space 接口文档")
                .contact(new Contact("Music Space", "www.baidu.com", "neilwang_94.qq.com"))
                .version("1.0")
                .build();
    }
}
