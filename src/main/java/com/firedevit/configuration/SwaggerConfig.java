package com.firedevit.configuration;


import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.firedevit.controller"))
				.paths(PathSelectors.any()).build().apiInfo(metadata()).useDefaultResponseMessages(false);
	}			

	public static ApiInfo metadata() {
		return new ApiInfoBuilder()
				.title("Challenge FireDev-IT")
				.description("Challenge")
				.version("1.0.0")
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/abnerwerley/challenge_back.git")
				.contact(contact()).build();
	}

	private static Contact contact() {
		return new Contact("Abner Werley Silva", "https://github.com/abnerwerley/challenge_back.git", "abnerwerley77@gmail.com");
	}

	@SuppressWarnings("unused")
	private static List<Response> responseMessage() {
		return new ArrayList<Response>() {
			private static final long serialVersionUID = 1L;
			{
				add(new ResponseBuilder().code("200").description("Sucesso.").build());
				add(new ResponseBuilder().code("201").description("Criado.").build());
				add(new ResponseBuilder().code("400").description("Erro na requisição.").build());
				add(new ResponseBuilder().code("401").description("Não Autorizado.").build());
				add(new ResponseBuilder().code("403").description("Proibido.").build());
				add(new ResponseBuilder().code("404").description("Não Encontrado.").build());
				add(new ResponseBuilder().code("500").description("Erro interno de servidor.").build());
			}
		};
	}
}