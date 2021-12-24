package com.tushar.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	 @Bean
	  public OpenAPI customOpenAPI() {
	    return new OpenAPI()
	        .components(new Components())
	        .info(new Info()
	            .title("Banking Operations")
	            .description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.")
	            .termsOfService("terms")
	            .contact(new Contact().email("tushar.khadase@gmail.com"))
	            .license(new License().name("GNU"))
	            .version("1.2 RELEASE")
	        );
	  }
	}