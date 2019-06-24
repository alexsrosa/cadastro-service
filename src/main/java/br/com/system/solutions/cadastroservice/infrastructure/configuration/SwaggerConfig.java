package br.com.system.solutions.cadastroservice.infrastructure.configuration;

import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe de configuração do Swagger.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT =
            new Contact("Cadastro",
                    "cadastro.com.br",
                    "cadastro@cadastro.com.br");
    public static final ApiInfo DEFAULT_API_INFO =
            new ApiInfo("Cadastro API",
                    "Cadastro API documentation sample",
                    "1.0", "urn:tos",
                    DEFAULT_CONTACT,
                    "Apache 2.0",
                    "http://www.apache.org/licenses/LICENSE-2.0");

    @Bean
    public Docket api() {
        Set<String> producesAndConsumes = new HashSet<>();
        producesAndConsumes.add("application/json");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(producesAndConsumes)
                .consumes(producesAndConsumes);

    }
}
