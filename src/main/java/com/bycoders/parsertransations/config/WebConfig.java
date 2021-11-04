package com.bycoders.parsertransations.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");

	
	@Bean
	public OpenAPI openApiConfig()	
	{
		return new OpenAPI().info(new Info().title("Bycoders-Transations RESTFul Web Services Aplication")
				.version("1.0.0")
				.description("Bycoders-Transations RESTFul Web Services Aplication")
				.termsOfService("http://bycoders.com.br/terms")
				.license(new License().name("Bycoders License")
				.url("https://www.bycoders.com.br/")));
	}
	
	
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
	}
	

}
