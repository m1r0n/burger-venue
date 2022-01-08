package ee.qminder.venues.json;

import java.util.List;

public record VenueSearchResponseJson(String cursor, List<VenueJson> venues) {
}
