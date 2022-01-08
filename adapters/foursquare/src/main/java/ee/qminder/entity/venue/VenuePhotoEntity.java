package ee.qminder.entity.venue;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VenuePhotoEntity {
    String id;
    String prefix;
    String suffix;
    long width;
    long height;
    @JsonProperty("created_at")
    LocalDateTime createdAt;
}
