package pe.edu.upc.skillswap.platform.skillswap_platform.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configures the OpenAPI specification exposed by the platform.
 */
@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI skillSwapPlatformOpenApi() {
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("SkillSwap Platform API")
                        .description("SkillSwap Platform application REST API documentation.")
                        .version("v1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("SkillSwap Platform Documentation")
                        .url("https://github.com/1asi0729-11990/skillswap"));

        openApi.servers(List.of(
                new Server()
                        .url("http://localhost:8097")
                        .description("Local Development Environment")
        ));

        return openApi;
    }
}