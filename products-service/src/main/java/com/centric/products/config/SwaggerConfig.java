package com.centric.products.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* Configuration for Swagger UI.
*/
@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfig {

	/**
	 * Swagger API bean.
	 * 
	 * @return the Docket bean.
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("products")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("com.centric.products.rest"))
				.paths(PathSelectors.any())
				.build();

	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("Products API", "Products API documentation.", "1.0", null,
				new Contact("Centric, Inc. Copyright (c) 2019", "https://www.centric.com/", null), null, null);
		return apiInfo;
	}

}
