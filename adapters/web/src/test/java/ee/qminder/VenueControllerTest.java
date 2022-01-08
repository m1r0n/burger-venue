package ee.qminder;

import ee.qminder.usecase.venue.GetVenues;
import ee.qminder.venues.VenueController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VenueControllerTest {

    private VenueController venueController;
    private GetVenues getVenues;

    @BeforeEach
    void setup() {
        getVenues = mock(GetVenues.class);
        venueController = new VenueController(getVenues);
    }

    @Test
    void getTartuVenues_withNoPointer_shouldPresentData() {
        when(getVenues.execute(any())).thenReturn(TestData.mockResponse());

        var response = venueController.getTartuVenues(Optional.empty());

        assertThat(Objects.requireNonNull(response.getBody()).venues().get(0)).satisfies(venueJson -> {
            assertThat(venueJson.name()).isEqualTo(TestData.NAME);
            assertThat(venueJson.description()).isEqualTo(TestData.DESCRIPTION);
            assertThat(venueJson.photoUrl()).isEqualTo(TestData.URL);
        });
        assertThat(Objects.requireNonNull(response.getBody()).cursor()).isEqualTo(TestData.CURSOR);
    }
}
