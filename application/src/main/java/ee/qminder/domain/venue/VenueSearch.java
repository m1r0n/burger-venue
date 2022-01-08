package ee.qminder.domain.venue;

import java.util.List;

public record VenueSearch(String name, String description, List<VenuePhoto> photos) {
}
