package ee.qminder.domain.venue;

import java.time.LocalDateTime;

public record VenuePhoto(String id, LocalDateTime createdAt, String url) {
}
