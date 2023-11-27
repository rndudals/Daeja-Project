package parkingLotG.parkinglotG.Swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(title = "Couple App",
                description = "couple app api명세",
                version = "v1")
)
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi allOpenApi() {
        return GroupedOpenApi.builder()
                .group("All APIs")
                .packagesToScan("parkingLotG.parkinglotG") // Scan all packages for APIs
                .build();
    }
}
