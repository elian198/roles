package com.roles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

        @Bean
        public Docket apiDocket() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.roles"))
                    .paths(PathSelectors.any())
                    .build()
                    .apiInfo(getApiInfo())
                    ;
        }

        private ApiInfo getApiInfo() {
            return new ApiInfo(
                    "Aplicacion ROLES",
                    "Api de tipo Rest basada en roles y seguridad de tipo JWT",
                    "1.0",
                    "http://google.com",
                    new Contact("Elian", "https://www.linkedin.com/in/elian-pareja/", "elianelian5@gmail.com"),
                    "MIT",
                    "https://github.com/elian198",
                    Collections.emptyList()
            );
        }

    }

