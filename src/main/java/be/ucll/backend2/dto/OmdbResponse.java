package be.ucll.backend2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// Because we can get different kinds of responses, we're trying to differentiate based on which response we get
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "Response")
@JsonSubTypes({
        @JsonSubTypes.Type(value = OmdbResponse.Movie.class, name = "True"),
        @JsonSubTypes.Type(value = OmdbResponse.Error.class, name = "False")
})
public interface OmdbResponse {
    record Movie(
            @JsonProperty("Title") String title,
            @JsonProperty("Year") String year,
            @JsonProperty("Plot") String plot
    ) implements OmdbResponse {}

    record Error(
            @JsonProperty("Error") String error
    ) implements OmdbResponse {}
}
