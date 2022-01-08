package ee.qminder.venue;

import ee.qminder.domain.venue.VenuePhoto;
import ee.qminder.domain.venue.VenueSearch;
import ee.qminder.domain.venue.VenueSearchResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TestData {

    public static final String NAME1 = "name1";
    public static final String DESCRIPTION1 = "description1";
    public static final String PHOTO_ID1 = "photoID1";
    public static final String PHOTO_ID2 = "photoID2";
    public static final String IMAGE_URL1 = "imageUrl1";
    public static final String IMAGE_URL2 = "imageUrl2";
    public static final String CURSOR = "cursor";

    public static VenueSearchResponse createTestVenueResponse() {
        return new VenueSearchResponse(createTestCursor(), createTestVenueSearches());
    }

    private static List<VenueSearch> createTestVenueSearches() {
        return List.of(new VenueSearch(NAME1, DESCRIPTION1, createTestVenuePhotos()));
    }

    private static List<VenuePhoto> createTestVenuePhotos() {
        LocalDateTime time = LocalDateTime.of(2021,12,30,12,30,30);
        return List.of(
                new VenuePhoto(PHOTO_ID1, time, IMAGE_URL1),
                new VenuePhoto(PHOTO_ID2, time.plusDays(1), IMAGE_URL2)
        );
    }

    private static Optional<String> createTestCursor() {
        return Optional.of(CURSOR);
    }
}
