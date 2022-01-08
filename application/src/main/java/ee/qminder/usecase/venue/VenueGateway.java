package ee.qminder.usecase.venue;

import ee.qminder.domain.venue.VenueSearchResponse;

import java.util.Optional;

public interface VenueGateway {

    VenueSearchResponse getVenues(Optional<String> cursor);
}
