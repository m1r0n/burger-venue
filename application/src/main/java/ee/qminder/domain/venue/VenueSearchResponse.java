package ee.qminder.domain.venue;

import java.util.List;
import java.util.Optional;

public record VenueSearchResponse(Optional<String> cursor, List<VenueSearch> venues) {
}
