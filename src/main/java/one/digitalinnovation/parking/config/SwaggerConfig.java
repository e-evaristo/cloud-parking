package one.digitalinnovation.parking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("one.digitalinnovation.parking.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metadata())
                .securityContexts(Arrays.asList(getSecurityContext()))
                .securitySchemes(Arrays.asList(basicAuthScheme()));
        }

    private ApiInfo metadata() {
        //return new ApiInfo("Parking Api", "Parking Api Documentation", "1.0", "urn:tos", null, "Apache 2.0", "https://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
        return new ApiInfoBuilder()
                .title("Parking Api")
                .description("Parking Api Documentation")
                .version("1.0")
                .license("Apache 2.0")
                .build();
    }

    private SecurityScheme basicAuthScheme() {
        return new BasicAuth("basicAuth");
    }

    private SecurityContext getSecurityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(basicAuthReference()))
                .build();
    }

    private SecurityReference basicAuthReference() {
        return new SecurityReference("basicAuth", new AuthorizationScope[0]);
    }
}
