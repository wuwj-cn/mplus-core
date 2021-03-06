package com.mplus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 在线api访问url：http://localhost:8080/swagger-ui.html
 * @author wuwj
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.mplus.core.web"))
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("mplus利用swagger构建api文档")
				.description("简单优雅的restfun风格，https://github.com/wuwj-cn/mplus-core")
				.termsOfServiceUrl("https://github.com/wuwj-cn/mplus-core")
				.version("1.0")
				.build();
	}
}
