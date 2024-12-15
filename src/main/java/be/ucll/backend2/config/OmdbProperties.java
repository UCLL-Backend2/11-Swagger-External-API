package be.ucll.backend2.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

// Add omdb configuration properties under the omdb prefix
@ConfigurationProperties(prefix = "omdb")
public record OmdbProperties(
        @DefaultValue("https://www.omdbapi.com") String baseUrl,
        String apiKey) {
}
