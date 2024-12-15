package be.ucll.backend2;

import be.ucll.backend2.exception.OmdbException;
import be.ucll.backend2.service.OmdbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class OmdbServiceIntegrationTest {
    @Autowired
    private OmdbService omdbService;

    @Test
    public void givenMovieExists_whenRetrievingMovieByTitle_thenMovieIsRetrieved() throws OmdbException {
        final var movie = omdbService.getOmdbMovieForTitle("The Shawshank Redemption");

        Assertions.assertEquals("The Shawshank Redemption", movie.title());
        Assertions.assertFalse(movie.plot().isEmpty());
        Assertions.assertEquals("1994", movie.year());
    }

    @Test
    public void givenMovieDoesntExist_whenRetrievingMovieByTitle_thenMovieIsNotRetrieved() {
        final var exception = Assertions.assertThrows(OmdbException.class, () ->
            omdbService.getOmdbMovieForTitle("sdfgrgoeroijgero")
        );

        Assertions.assertEquals("Movie not found!", exception.getMessage());
    }
}
