package com.waslabrowser.service.common.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Predicate;

@Configuration
public class SwaggerConfig {

	@Value("${swagger.enabled}")
	private boolean isSwaggerEnabled;

	//baseurl/swagger-ui/
	@Bean
	public Docket api() {
		final Predicate<String> pathsSelector;
		if (isSwaggerEnabled) pathsSelector = PathSelectors.any();
		else pathsSelector = PathSelectors.none();
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(pathsSelector)
			.build();
	}

}