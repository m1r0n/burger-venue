package ee.qminder.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

public class FourSquareRestConfigService {

    private static final String BASE_URL = "https://api.foursquare.com/v3/places";
    private static final String SEARCH = "/search";

    private final String token;

    public FourSquareRestConfigService(String token) {
        this.token = token;
    }

    public HttpHeaders getHttpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.add("Authorization", token);
        return httpHeaders;
    }

    public String getSearcVenuesUrlTemplate(boolean withCursor) {
        var uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(BASE_URL + SEARCH)
                .queryParam("near", "{near}")
                .queryParam("limit", "{limit}")
                .queryParam("categories", "{categories}")
                .queryParam("fields", "{fields}");
        if (withCursor) {
            uriComponentsBuilder.queryParam("cursor", "{cursor}");
        }

        return uriComponentsBuilder
                .encode()
                .toUriString();
    }
}
