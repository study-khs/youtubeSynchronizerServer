package study.khs.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket messageApi() {
		String apiName = "Message Api";

		ApiInfo apiInfo = new ApiInfoBuilder()//
				.title(apiName)//
				.description(apiName.concat(" 확인페이지"))//
				.build();

		return new Docket(DocumentationType.SWAGGER_2)//
				.apiInfo(apiInfo)//
				.groupName(apiName)//
				.select()//
				.apis(RequestHandlerSelectors.basePackage("study.khs.api"))//
				.paths(PathSelectors.ant("/api/message/**"))//
				.build();
	}

	@Bean
	public Docket memberApi() {
		String apiName = "Member Api";

		ApiInfo apiInfo = new ApiInfoBuilder()//
				.title(apiName)//
				.description(apiName.concat(" 확인페이지"))//
				.build();

		return new Docket(DocumentationType.SWAGGER_2)//
				.apiInfo(apiInfo)//
				.groupName(apiName)//
				.select()//
				.apis(RequestHandlerSelectors.basePackage("study.khs.api"))//
				.paths(PathSelectors.ant("/api/member/**"))//
				.build();
	}

	@Bean
	public Docket channelApi() {
		String apiName = "Channel Api";

		ApiInfo apiInfo = new ApiInfoBuilder()//
				.title(apiName)//
				.description(apiName.concat(" 확인페이지"))//
				.build();

		return new Docket(DocumentationType.SWAGGER_2)//
				.apiInfo(apiInfo)//
				.groupName(apiName)//
				.select()//
				.apis(RequestHandlerSelectors.basePackage("study.khs.api"))//
				.paths(PathSelectors.ant("/api/member/**"))//
				.build();
	}
}
