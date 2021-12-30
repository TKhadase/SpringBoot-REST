package com.tushar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootApplication
public class SbRest09RestTemplateConsumerAppApplication {

	@Bean
	public RestTemplate createRestTemplate() {
		System.out.println("SbRest09RestTemplateConsumerAppApplication.createRestTemplate()");
		return new RestTemplate();
	}
	
	@Bean
	public ObjectMapper createObjectMapper() {
		System.out.println("SbRest09RestTemplateConsumerAppApplication.createObjectMapper()");
		return new ObjectMapper().registerModule(new JavaTimeModule());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SbRest09RestTemplateConsumerAppApplication.class, args);
	}

}
