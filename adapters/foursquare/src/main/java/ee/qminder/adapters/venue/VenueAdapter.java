package ee.qminder.adapters.venue;

import ee.qminder.common.annotation.ExternalAdapter;
import ee.qminder.common.mapper.RestObjectMapper;
import ee.qminder.domain.venue.VenueSearchResponse;
import ee.qminder.entity.venue.response.SearchVenuesResponseBody;
import ee.qminder.usecase.venue.VenueGateway;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@ExternalAdapter
public class VenueAdapter implements VenueGateway {

    private final FourSquareRestService fourSquareRestService;
    private final RestObjectMapper restObjectMapper;

    public VenueAdapter(FourSquareRestService fourSquareRestService, RestObjectMapper restObjectMapper) {
        this.fourSquareRestService = fourSquareRestService;
        this.restObjectMapper = restObjectMapper;
    }

    @Override
    public VenueSearchResponse getVenues(Optional<String> cursor) {
        ResponseEntity<String> foursquareResponse = fourSquareRestService.searchVenues(cursor);
        Optional<String> responseCursor = extractCursor(foursquareResponse.getHeaders());
        SearchVenuesResponseBody response = restObjectMapper.mapResponse(foursquareResponse.getBody(), SearchVenuesResponseBody.class);
        return new VenueSearchResponse(responseCursor, VenueMapper.toVenueSearchList(response.getResults()));
    }

    private Optional<String> extractCursor(HttpHeaders httpHeaders) {
        List<String> link = httpHeaders.get("link");
        return link == null || link.isEmpty()
                ? Optional.empty()
                : VenueMapper.extractCursor(link.get(0));
    }


}
