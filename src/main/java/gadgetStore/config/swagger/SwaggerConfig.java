package gadgetStore.config.swagger;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Collections;

@Configuration
//@OpenAPIDefinition(
//        info = @Info(
//                title = "Gadget Store API",
//                description = "This is the documentation for the Gadget Store backend",
//                version = "1.0"
//        )
//)
public class SwaggerConfig {
    private final String API_KEY = "bearer Token";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(API_KEY, apiKeySecurityScheme()))
                .info(new Info()
                        .title("Gadget Store")
                        .description("Java17 Gadget Store project"))
                .security(Collections.singletonList(new SecurityRequirement().addList(API_KEY)));
    }


    public SecurityScheme apiKeySecurityScheme() {
        return new SecurityScheme()
                .name("AUTH API")
                .description("Please put the token")
                .in(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer");
    }
}
