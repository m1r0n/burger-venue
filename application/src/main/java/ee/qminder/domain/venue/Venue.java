package ee.qminder.domain.venue;

import java.util.Optional;

public record Venue(String name, String description, Optional<String> photoUrl) {
}
