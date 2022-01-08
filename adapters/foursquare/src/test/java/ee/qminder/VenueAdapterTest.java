package ee.qminder;

import ee.qminder.adapters.venue.FourSquareRestService;
import ee.qminder.adapters.venue.VenueAdapter;
import ee.qminder.common.mapper.RestObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VenueAdapterTest {

    @Mock
    private FourSquareRestService fourSquareRestService;
    @Mock
    private RestObjectMapper restObjectMapper;
    private VenueAdapter venueAdapter;

    @BeforeEach
    void setup() {
        venueAdapter = new VenueAdapter(fourSquareRestService, restObjectMapper);
    }

    @Test
    void getVenues_withNoCursor_shouldReturn() {
        when(fourSquareRestService.searchVenues(any())).thenReturn(ResponseEntity.ok(""));
        when(restObjectMapper.mapResponse(anyString(), any())).thenReturn(TestData.getSearchVenueResponseBody());

        var venueSearchResponse = venueAdapter.getVenues(Optional.empty());
        assertThat(venueSearchResponse.cursor()).isEmpty();
        assertThat(venueSearchResponse.venues()).hasSize(1);
        assertThat(venueSearchResponse.venues().get(0)).satisfies(venue -> {
            assertThat(venue.name()).isEqualTo(TestData.NAME);
            assertThat(venue.description()).isEqualTo(TestData.DESCRIPTION);
            assertThat(venue.photos()).hasSize(1);
            assertThat(venue.photos().get(0).url()).isEqualTo(TestData.PREFIX + "original" + TestData.SUFFIX);
        });
    }

}
