package ee.qminder.entity.venue;

import lombok.Data;

import java.util.List;

@Data
public class VenueEntity {
    String fsq_id;
    String name;
    String description;
    List<VenuePhotoEntity> photos;
}
