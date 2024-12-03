package com.paulojr.cotacao.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Mercado de Pulgas dos Mil Saberes")
                        .description("API para o gerenciamento de conversão de moedas e produtos entre Ouro Real e Tibar, conforme o desafio técnico.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Paulo Júnior")
                                .email("pcteixeira@gmail.com"))
                        .license(new License()
                                .name("Licença MIT")
                                .url("https://opensource.org/licenses/MIT"))
                )

                .externalDocs(new ExternalDocumentation()
                        .description("Documentação completa do sistema Mercado SRM"))
                .addTagsItem(new Tag().name("Moedas").description("Endpoints relacionados à conversão de moedas"))
                .addTagsItem(new Tag().name("Produtos").description("Endpoints relacionados à conversão e gerenciamento de produtos"));
    }
}
