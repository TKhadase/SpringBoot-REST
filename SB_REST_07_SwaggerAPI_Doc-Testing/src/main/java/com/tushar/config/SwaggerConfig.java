package com.tushar.config;

import java.util.Collections;

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
	public Docket getDocket() {
		System.out.println("SwaggerConfig.getDocket()");
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("com.tushar.controller"))
				.paths(PathSelectors.regex("/Accounts.*"))
				.build()
				.useDefaultResponseMessages(true)
				.apiInfo(getAPIInfo());
				
	}
	
	private ApiInfo getAPIInfo() {
		System.out.println("SwaggerConfig.getAPIInfo()");
		Contact contact =new Contact("Tushar Khadse", "https://www.linkedin.com/in/Tkhadase/", "tushar.khadase@gmail.com");
		return new ApiInfo("Banking Operation API", 
				"Retails", 
				"1.2 RELEASE", 
				"https://github.com/TKhadase", 
				contact,
				 "GPU" , 
				 "www.gpulicence.com",Collections.emptyList());
		
	}
	
}
