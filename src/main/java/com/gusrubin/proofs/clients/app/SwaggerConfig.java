package com.gusrubin.proofs.clients.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Value( "${springfox.host}" )
	private String host;
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .host(host)
          .groupName("api-clientes-service")
          .tags(getTags("Gerenciamento de Clientes", "Cadastro - Atualização - Consulta - Exclusão"))
          .useDefaultResponseMessages(false)
          .globalResponseMessage(RequestMethod.POST, responseMessageList())
          .globalResponseMessage(RequestMethod.PATCH, responseMessageList())
          .globalResponseMessage(RequestMethod.GET, responseMessageList())
          .globalResponseMessage(RequestMethod.DELETE, responseMessageList())
          .select()                            
          .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
          .paths(PathSelectors.any())                        
          .build()
          .apiInfo(apiInfo());
    }
	
	private Tag getTags(String tag, String description) {
		Tag newTag = new Tag(tag, description);
		return newTag;
	}
	
	private ApiInfo apiInfo() {
	     return new ApiInfo(
	       "Gerenciador de Clientes", 
	       "API do serviço de gerenciamento de clientes.", 
	       "v1", 
	       "Terms of service", 
	       new Contact("Gustavo Rubin", "https://www.gusrubin.eng.br", "gusrubin@gmail.com"), 
	       "License of API", "https://www.gusrubin.eng.br/en/license", Collections.emptyList());
	}
	
	private List<ResponseMessage> responseMessageList() {
		List<ResponseMessage> response = new ArrayList<>();
		response.add(new ResponseMessageBuilder() 
	            .code(HttpStatus.SC_BAD_REQUEST)
	            .message("Bad Request")
	            .build());
		response.add(new ResponseMessageBuilder() 
	            .code(HttpStatus.SC_PRECONDITION_FAILED)
	            .message("Precondition Failed")
	            .build());
		response.add(new ResponseMessageBuilder() 
	            .code(HttpStatus.SC_UNPROCESSABLE_ENTITY)
	            .message("Unprocessable entity")
	            .build());
		response.add(new ResponseMessageBuilder()
				.code(HttpStatus.SC_INTERNAL_SERVER_ERROR)
	            .message("Internal Server Error")
	            .build());           
		return response;
	}

}
