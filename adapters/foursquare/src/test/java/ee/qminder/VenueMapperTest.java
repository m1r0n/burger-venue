package ee.qminder;

import ee.qminder.adapters.venue.VenueMapper;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class VenueMapperTest {

    @Test
    void extractCursor_withValidLink_shouldFindCursor() {
        String link = "<https://api.foursquare.com/v3/places/search?cursor=c3I6NjA&near=tartu&fields=fsq_id%2Cname%2Cdescription%2Cphotos&categories=13000&limit=10>; rel=\"next\"";
        Optional<String> cursor = VenueMapper.extractCursor(link);
        assertThat(cursor).hasValue("c3I6NjA");
    }

    @Test
    void extractCursor_withValidLinkAndCursorLastParameter_shouldFindCursor() {
        String link = "<https://api.foursquare.com/v3/places/search?near=tartu&fields=fsq_id%2Cname%2Cdescription%2Cphotos&categories=13000&limit=10&cursor=c3I6NjA>; rel=\"next\"";
        Optional<String> cursor = VenueMapper.extractCursor(link);
        assertThat(cursor).hasValue("c3I6NjA");
    }

    @Test
    void extractCursor_withNoCursor_shouldReturnEmptyOptional() {
        String link = "<https://api.foursquare.com/v3/places/search?near=tartu&fields=fsq_id%2Cname%2Cdescription%2Cphotos&categories=13000&limit=10>; rel=\"next\"";
        Optional<String> cursor = VenueMapper.extractCursor(link);
        assertThat(cursor).isEmpty();
    }
}
