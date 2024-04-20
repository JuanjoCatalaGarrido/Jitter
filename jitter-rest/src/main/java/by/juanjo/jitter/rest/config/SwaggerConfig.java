package by.juanjo.jitter.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(this.provideOpenAPIInfo()).externalDocs(new ExternalDocumentation()
                .description("Documentation externa").url("https://springshop.wiki.github.org/docs"));

    }

    private Info provideOpenAPIInfo() {
        return new Info().title("Jitter API").description("Integración de swagger-ui en la API de Jitter. ")
                .version("v0.0.1").license(new License().name("Apache 2.0").url("http://springdoc.org"));

    }

}
