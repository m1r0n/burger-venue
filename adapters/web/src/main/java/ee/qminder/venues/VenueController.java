package ee.qminder.venues;

import ee.qminder.common.annotation.WebAdapter;
import ee.qminder.usecase.venue.GetVenues;
import ee.qminder.venues.json.VenueSearchResponseJson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@WebAdapter
@RestController
@RequestMapping("api/venues")
@RequiredArgsConstructor
public class VenueController {

    private final GetVenues getVenues;

    @GetMapping({"", "/cursor/{cursor}"})
    public ResponseEntity<VenueSearchResponseJson> getTartuVenues(@PathVariable Optional<String> cursor) {
        var response = getVenues.execute(new GetVenues.Request(cursor));
        var presenter = new VenuePresenter();

        presenter.present(response);
        return presenter.getViewModel();
    }

}
