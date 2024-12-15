package be.ucll.backend2.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(OmdbProperties.class)
public class OmdbConfig {
    // Declare a WebClient we can use in the service
    @Bean
    public WebClient omdbClient(OmdbProperties omdbProperties) {
        return WebClient.builder()
                // Set the base URL: every request will start from this base URL
                .baseUrl(omdbProperties.baseUrl())
                // Sets some default URI variables, every occurrence of {apikey} will be filled in with this
                .defaultUriVariables(Map.of("apikey", omdbProperties.apiKey()))
                .build();
    }
}
