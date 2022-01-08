package ee.qminder;

import ee.qminder.entity.venue.VenueEntity;
import ee.qminder.entity.venue.VenuePhotoEntity;
import ee.qminder.entity.venue.response.SearchVenuesResponseBody;

import java.time.LocalDateTime;
import java.util.List;

public class TestData {

    public static final String NAME = "name";
    public static final String FSQ_ID = "fsq_id";
    public static final String DESCRIPTION = "description";
    public static final String PHOTO_ID = "id";
    public static final String PREFIX = "prefix";
    public static final String SUFFIX = "suffix";

    public static SearchVenuesResponseBody getSearchVenueResponseBody() {
        var searchResultBody = new SearchVenuesResponseBody();
        searchResultBody.setResults(mockVenueEntities());
        return searchResultBody;
    }

    private static List<VenueEntity> mockVenueEntities() {
        VenueEntity venueEntity = new VenueEntity();
        venueEntity.setName("name");
        venueEntity.setFsq_id("fsq_id");
        venueEntity.setDescription("description");
        venueEntity.setPhotos(mockVenueEntityPhotos());
        return List.of(venueEntity);
    }

    private static List<VenuePhotoEntity> mockVenueEntityPhotos() {
        VenuePhotoEntity venuePhotoEntity = new VenuePhotoEntity();
        venuePhotoEntity.setId("id");
        venuePhotoEntity.setPrefix("prefix");
        venuePhotoEntity.setSuffix("suffix");
        venuePhotoEntity.setCreatedAt(LocalDateTime.now());
        return List.of(venuePhotoEntity);
    }

}
