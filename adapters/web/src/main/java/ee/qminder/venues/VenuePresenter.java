package ee.qminder.venues;

import ee.qminder.domain.venue.Venue;
import ee.qminder.usecase.venue.GetVenues.Response;
import ee.qminder.venues.json.VenueJson;
import ee.qminder.venues.json.VenueSearchResponseJson;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

public class VenuePresenter {

    VenueSearchResponseJson result;

    public void present(Response response) {
        result = new VenueSearchResponseJson(
                response.cursor().orElse(null),
                response.venues().stream().map(this::toVenueJson).toList());
    }

    private VenueJson toVenueJson(Venue venue) {
        return new VenueJson(venue.name(), venue.description(), venue.photoUrl().orElse(null));
    }

    public ResponseEntity<VenueSearchResponseJson> getViewModel() {
        Assert.notNull(result, () -> "Result must be presented");
        return ResponseEntity.ok(result);
    }
}
