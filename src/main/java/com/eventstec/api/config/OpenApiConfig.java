package com.eventstec.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API para gerenciamento de eventos")
                        .version("1.0")
                        .description("Api para gerenciamento de eventos que irão acontecer")
                        .contact(new Contact()
                                .name("William José Dias")
                                .email("william.jose.wjd@gmail.com")
                                .url("https://github.com/williamwjd"))
                        .license(new License()
                                .name("Licença de Uso")
                                .url("")))
                .servers(Collections.singletonList(new Server().url("http://localhost:8080")))
//                .addTagsItem(new io.swagger.v3.oas.models.tags.Tag().name("Eventos").description("Operações relacionadas a eventos de programação"))
                .addSecurityItem(new SecurityRequirement());
    }
}
