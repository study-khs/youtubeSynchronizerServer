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
		return new Docket(DocumentationType.SWAGGER_2).groupName("message").select()
				.apis(RequestHandlerSelectors.basePackage("study.khs.api")).paths(PathSelectors.ant("/api/message/**"))
				.build().apiInfo(apiInfo());
	}

	@Bean
	public Docket memberApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("member").select()
				.apis(RequestHandlerSelectors.basePackage("study.khs.api")).paths(PathSelectors.ant("/api/member/**"))
				.build().apiInfo(apiInfo());
	}

	@Bean
	public Docket channelApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("channel").select()
				.apis(RequestHandlerSelectors.basePackage("study.khs.api")).paths(PathSelectors.ant("/api/channel/**"))
				.build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("API SPEC 확인 페이지").description("API SPEC을 확인하기 위한 페이지입니다").build();
	}
}
