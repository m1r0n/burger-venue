package ee.qminder.venue;

import ee.qminder.usecase.burgerfinder.BurgerFinderGateway;
import ee.qminder.usecase.venue.GetVenues;
import ee.qminder.usecase.venue.VenueGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetVenuesTest {

    @Captor
    private ArgumentCaptor<List<String>> burgerFinderArgumentCaptor;

    private VenueGateway venueGateway;
    private BurgerFinderGateway burgerFinderGateway;
    private GetVenues getVenues;

    @BeforeEach
    void setup() {
        venueGateway = mock(VenueGateway.class);
        burgerFinderGateway = mock(BurgerFinderGateway.class);
        getVenues = new GetVenues(venueGateway, burgerFinderGateway);
    }

    @Test
    void execute_withNoCursor_shouldRetrieveData() {
        when(venueGateway.getVenues(Optional.empty())).thenReturn(TestData.createTestVenueResponse());
        when(burgerFinderGateway.getImageUrlContainingBurger(anyList())).thenReturn(Optional.of(TestData.IMAGE_URL1));

        var response = getVenues.execute(new GetVenues.Request(Optional.empty()));

        assertThat(response.venues()).hasSize(1);
        assertThat(response.venues().get(0).name()).isEqualTo(TestData.NAME1);
        assertThat(response.venues().get(0).description()).isEqualTo(TestData.DESCRIPTION1);
        assertThat(response.venues().get(0).photoUrl()).hasValue(TestData.IMAGE_URL1);

        verify(venueGateway).getVenues(any());
        verify(burgerFinderGateway).getImageUrlContainingBurger(anyList());
    }

    @Test
    void execute_burgerVenueCalledWithCorrectPhotosOrder() {
        when(venueGateway.getVenues(Optional.empty())).thenReturn(TestData.createTestVenueResponse());
        when(burgerFinderGateway.getImageUrlContainingBurger(burgerFinderArgumentCaptor.capture())).thenReturn(Optional.of(TestData.IMAGE_URL1));

        getVenues.execute(new GetVenues.Request(Optional.empty()));

        List<String> photoUrls = burgerFinderArgumentCaptor.getValue();
        assertThat(photoUrls).hasSize(2);
        assertThat(photoUrls.get(0)).isEqualTo(TestData.IMAGE_URL2);
        assertThat(photoUrls.get(1)).isEqualTo(TestData.IMAGE_URL1);
    }


}
