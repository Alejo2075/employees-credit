package org.alejo2075.employees_credit.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Swagger/OpenAPI documentation.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Bean for grouping public APIs.
     *
     * @return the grouped OpenApi instance
     */
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .packagesToScan("org.alejo2075.employees_credit.modules.user.controller")
                .build();
    }

    /**
     * Bean for custom OpenAPI configuration.
     *
     * @return the OpenAPI instance with custom info
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employees Credit API")
                        .version("1.0")
                        .description("API documentation for the Employees Credit application")
                        .contact(new Contact().name("Support Team").email("alejsant75@gmail.com").url("https://github.com/Alejo2075/employees-credit"))
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")));
    }
}