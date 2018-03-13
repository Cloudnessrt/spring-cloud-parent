package com.tomorrow.monitorbusi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author tch
 */
/*restful api Swagger2 配置*/  /*http://localhost:8767/monitor-busi-user/swagger-ui.html#/*/
@Configuration
@EnableSwagger2
public class Swagger2 {
    //web包的位置
    @Value("${tchproject.web.packet}")
    private String name;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(name))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("使用Swagger2构建RESTful APIs")
                .description("monitor-busi-user用户")
                .termsOfServiceUrl("tanghecaijing@outlook.com")
                .contact("tangchenghui")
                .version("1.0")
                .build();
    }

}
