package ee.qminder.adapters.venue;

import ee.qminder.domain.venue.VenuePhoto;
import ee.qminder.domain.venue.VenueSearch;
import ee.qminder.entity.venue.VenueEntity;
import ee.qminder.entity.venue.VenuePhotoEntity;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VenueMapper {

    public static Optional<String> extractCursor(String link) {
        Pattern pattern = Pattern.compile(".*cursor=([a-zA-Z0-9]+).*");
        Matcher matcher = pattern.matcher(link);
        if (matcher.find()) {
            return Optional.of(matcher.group(1));
        }
        return Optional.empty();
    }

    public static List<VenueSearch> toVenueSearchList(List<VenueEntity> venueEntities) {
        return venueEntities.stream()
                .map(VenueMapper::toVenueSearch)
                .toList();
    }

    private static VenueSearch toVenueSearch(VenueEntity venueEntity) {
        return new VenueSearch(
                venueEntity.getName(),
                venueEntity.getDescription(),
                venueEntity.getPhotos().stream().map(VenueMapper::toVenuePhoto).toList()
        );
    }

    private static VenuePhoto toVenuePhoto(VenuePhotoEntity entity) {
        return new VenuePhoto(
                entity.getId(),
                entity.getCreatedAt(),
                toVenuePhotoUrl(entity.getPrefix(), entity.getSuffix())
        );
    }

    private static String toVenuePhotoUrl(String prefix, String suffix) {
        return prefix + "original" + suffix;
    }
}
