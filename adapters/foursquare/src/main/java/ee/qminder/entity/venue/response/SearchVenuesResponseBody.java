package ee.qminder.entity.venue.response;

import ee.qminder.entity.venue.VenueEntity;
import ee.qminder.entity.venue.response.context.SearchResultContext;
import lombok.Data;

import java.util.List;

@Data
public class SearchVenuesResponseBody {
    SearchResultContext context;
    List<VenueEntity> results;
}
