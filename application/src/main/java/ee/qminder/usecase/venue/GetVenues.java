package ee.qminder.usecase.venue;

import ee.qminder.common.annotation.UseCase;
import ee.qminder.domain.venue.Venue;
import ee.qminder.domain.venue.VenuePhoto;
import ee.qminder.domain.venue.VenueSearch;
import ee.qminder.usecase.burgerfinder.BurgerFinderGateway;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class GetVenues {

    private final VenueGateway venueGateway;
    private final BurgerFinderGateway burgerFinderGateway;

    public Response execute(Request request) {
        var venueSearchResponse = venueGateway.getVenues(request.cursor);
        List<Venue> venues = filterVenues(venueSearchResponse.venues()); // TODO: 08/01/2022 Bad naming
        return new Response(venueSearchResponse.cursor(), venues);
    }

    private List<Venue> filterVenues(List<VenueSearch> venues) {
        return venues.stream().map(this::toVenue).toList();
    }

    private Venue toVenue(VenueSearch venueSearch) {
        Optional<String> burgerUrl = burgerFinderGateway.getImageUrlContainingBurger(getUrls(venueSearch.photos()));
        return new Venue(venueSearch.name(), venueSearch.description(), burgerUrl);
    }

    private List<String> getUrls(List<VenuePhoto> photos) {
        return photos.stream()
                .sorted(Comparator.comparing(VenuePhoto::createdAt).reversed())
                .map(VenuePhoto::url)
                .toList();
    }

    public record Response(Optional<String> cursor, List<Venue> venues) {}

    public record Request(Optional<String> cursor) {} // TODO: 08/01/2022 Add some validation and potential exception handling
}
