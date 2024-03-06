package com.enigmacamp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Music-API Documentations")
						.description("Documentations for Music-App REST-API").version("0.0.1")
						.license(new License().url("http://localhost:8081/music-app"))
						.contact(new Contact().name("edhi.uchiha").url("edhi.uchiha.com").email("edhi.uchiha@gmail.com"))
						.license(new License().name("License of API")));
	}

}
