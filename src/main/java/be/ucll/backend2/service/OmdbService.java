package be.ucll.backend2.service;

import be.ucll.backend2.dto.OmdbResponse;
import be.ucll.backend2.exception.OmdbException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Map;

@Service
public class OmdbService {
    private final WebClient omdbClient;

    public OmdbService(WebClient omdbClient) {
        this.omdbClient = omdbClient;
    }

    public OmdbResponse.Movie getOmdbMovieForTitle(String title) throws OmdbException {
        final ResponseEntity<OmdbResponse> result = omdbClient.get()
                .uri("/?t={title}&r=json&plot=full&apikey={apikey}", Map.of("title", title))
                .retrieve()
                .toEntity(OmdbResponse.class)
                .block(Duration.ofSeconds(10L));

        if (result != null && result.hasBody()) {
            final var body = result.getBody();
            if (body instanceof OmdbResponse.Movie movie) {
                return movie;
            } else if (body instanceof OmdbResponse.Error error) {
                throw new OmdbException(error.error());
            } else {
                throw new AssertionError("Unexpected body: " + body);
            }
        } else {
            throw new OmdbException("Failed to get response from OMDB");
        }
    }
}
