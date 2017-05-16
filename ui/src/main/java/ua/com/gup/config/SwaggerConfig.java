package ua.com.gup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apiItproekt() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("itproekt")
                .select()
                .apis(RequestHandlerSelectors.basePackage("ua.com.itproekt.gup"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket apiAdvert() {
        ApiInfo apiInfo = new ApiInfo(
                "ADVERT REST API",
                "New api for advert service.",
                "API TOS",
                "Terms of service",
                new Contact("GUP", "gup.com.ua", "____"),
                "License of API",
                "API license URL");
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("advert")
                .select()
                .apis(RequestHandlerSelectors.basePackage("ua.com.gup.web"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo);
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "UI REST API",
                "Some custom description of API.",
                "API TOS",
                "Terms of service",
                new Contact("GUP", "gup.com.ua", "____"),
                "License of API",
                "API license URL");
        return apiInfo;
    }
}