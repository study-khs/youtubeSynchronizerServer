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
		String apiName = "message-api";

		ApiInfo apiInfo = new ApiInfoBuilder()//
				.title(apiName)//
				.description(apiName.concat(" 확인 페이지"))//
				.build();

		return new Docket(DocumentationType.SWAGGER_2)//
				.groupName(apiName)//
				.apiInfo(apiInfo)//
				.select()//
				.apis(RequestHandlerSelectors.basePackage("study.khs.api"))//
				.paths(PathSelectors.ant("/api/message/**"))//
				.build();
	}

	@Bean
	public Docket memberApi() {
		String apiName = "member-api";

		ApiInfo apiInfo = new ApiInfoBuilder()//
				.title(apiName)//
				.description(apiName.concat(" 확인 페이지"))//
				.build();

		return new Docket(DocumentationType.SWAGGER_2)//
				.groupName(apiName)//
				.apiInfo(apiInfo)//
				.select()//
				.apis(RequestHandlerSelectors.basePackage("study.khs.api"))//
				.paths(PathSelectors.ant("/api/member/**"))//
				.build();
	}

	@Bean
	public Docket channelApi() {
		String apiName = "channel-api";

		ApiInfo apiInfo = new ApiInfoBuilder()//
				.title(apiName)//
				.description(apiName.concat(" 확인 페이지"))//
				.build();

		return new Docket(DocumentationType.SWAGGER_2)//
				.groupName(apiName)//
				.apiInfo(apiInfo)//
				.select()//
				.apis(RequestHandlerSelectors.basePackage("study.khs.api"))//
				.paths(PathSelectors.ant("/api/channel/**"))//
				.build();
	}
}
