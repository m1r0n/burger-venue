package ee.qminder;

import ee.qminder.domain.venue.Venue;
import ee.qminder.usecase.venue.GetVenues;

import java.util.List;
import java.util.Optional;

public class TestData {

    public static final String NAME = "name";
    public static final String DESCRIPTION = "name";
    public static final String URL = "url";
    public static final String CURSOR = "cursor";

    public static GetVenues.Response mockResponse() {
        return new GetVenues.Response(Optional.of(CURSOR), mockVenues());
    }

    private static List<Venue> mockVenues() {
        return List.of(new Venue(NAME, DESCRIPTION, Optional.of(URL)));
    }
}
