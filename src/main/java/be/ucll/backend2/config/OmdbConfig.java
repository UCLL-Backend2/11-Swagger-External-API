package be.ucll.backend2.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(OmdbProperties.class)
public class OmdbConfig {
    @Bean
    public WebClient omdbClient(OmdbProperties omdbProperties) {
        return WebClient.builder()
                .baseUrl(omdbProperties.baseUrl())
                .defaultUriVariables(Map.of("apikey", omdbProperties.apiKey()))
                .build();
    }
}
