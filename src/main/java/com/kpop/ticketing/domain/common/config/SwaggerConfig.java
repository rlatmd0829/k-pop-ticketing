package com.kpop.ticketing.domain.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {

		Info info = new Info()
			.title("k-pop ticketing")
			.version("1.0.0")
			.description("k-pop ticketing API");

		return new OpenAPI()
			.info(info);
	}
}
