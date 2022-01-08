package ee.qminder.usecase.burgerfinder;

import java.util.List;
import java.util.Optional;

public interface BurgerFinderGateway {
    Optional<String> getImageUrlContainingBurger(List<String> urls);
}
