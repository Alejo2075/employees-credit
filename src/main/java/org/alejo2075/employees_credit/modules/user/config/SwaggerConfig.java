package org.alejo2075.employees_credit.modules.user.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .packagesToScan("org.alejo2075.gov_employees_credit.controller")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employees Credit API")
                        .version("1.0")
                        .description("API documentation for the Employees Credit application")
                        //.termsOfService("http://example.com/terms")
                        .contact(new Contact().name("Support Team").email("alejsant75@gmail.com").url("https://github.com/Alejo2075/employees-credit"))
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")));
    }
}
