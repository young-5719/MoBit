package com.tj703.mobit.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("mobit-api")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public OpenAPI mobitOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Mobit API")
                        .description("암호화폐 모의투자 거래소 API 문서")
                        .version("v1.0"));
    }
}