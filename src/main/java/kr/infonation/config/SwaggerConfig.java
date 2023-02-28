package kr.infonation.config;

import kr.infonation.common.dto.ResponseDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

    private ApiInfo apiInfo(){
        return new ApiInfo(
          "Infonation",
                "",
                "1.0",
                "",
                null,
                "License of API",
                "API License URL",
                Collections.emptyList()
        );

    }

    @Bean
    public Docket commonApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                //.ignoredParameterTypes(ResponseDto.class)
                .groupName("swg-group1")
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("kr.infonation.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
