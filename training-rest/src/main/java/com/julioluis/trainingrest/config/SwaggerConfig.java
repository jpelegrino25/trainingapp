package com.julioluis.trainingrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT = new Contact("Julio Luis", "www.julioluis.org", "jpelegrino@gmail.com");
    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Api Descriiption", "Api Description",
            "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

    private static final Set<String> DEFAULT_PRODUCE_CONSUMERS =
            new HashSet<>(Arrays.asList("application/json","application/xml"));

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(DEFAULT_API_INFO)
                    .produces(DEFAULT_PRODUCE_CONSUMERS);
    }
}
