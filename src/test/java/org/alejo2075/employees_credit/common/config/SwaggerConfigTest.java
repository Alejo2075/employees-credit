package org.alejo2075.employees_credit.common.config;

import org.junit.jupiter.api.Test;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.swagger.v3.oas.models.OpenAPI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SwaggerConfigTest {

    @Autowired
    private GroupedOpenApi publicApi;

    @Autowired
    private OpenAPI customOpenAPI;

    @Test
    void publicApiBeanShouldBeConfigured() {
        assertThat(publicApi).isNotNull();
    }

    @Test
    void customOpenAPIBeanShouldBeConfigured() {
        assertThat(customOpenAPI).isNotNull();
        assertThat(customOpenAPI.getInfo().getTitle()).isEqualTo("Employees Credit API");
        assertThat(customOpenAPI.getInfo().getVersion()).isEqualTo("1.0");
    }
}