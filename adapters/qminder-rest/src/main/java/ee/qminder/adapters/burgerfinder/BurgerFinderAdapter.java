package ee.qminder.adapters.burgerfinder;

import ee.qminder.common.annotation.ExternalAdapter;
import ee.qminder.common.exception.QminderException;
import ee.qminder.common.mapper.RestObjectMapper;
import ee.qminder.entity.BurgerFinderInput;
import ee.qminder.entity.BurgerFinderResponseBody;
import ee.qminder.usecase.burgerfinder.BurgerFinderGateway;

import java.util.List;
import java.util.Optional;

@ExternalAdapter
public class BurgerFinderAdapter implements BurgerFinderGateway {

    private final RestObjectMapper restObjectMapper;
    private final BurgerImageRestService burgerImageRestService;

    public BurgerFinderAdapter(RestObjectMapper restObjectMapper, BurgerImageRestService burgerImageRestService) {
        this.restObjectMapper = restObjectMapper;
        this.burgerImageRestService = burgerImageRestService;
    }

    @Override
    public Optional<String> getImageUrlContainingBurger(List<String> urls) {
        BurgerFinderInput burgerFinderInput = new BurgerFinderInput(urls);
        try {
            String response = burgerImageRestService.getUrlsWithBurger(burgerFinderInput).getBody();
            BurgerFinderResponseBody burgerFinderResponseBody = restObjectMapper.mapResponse(response, BurgerFinderResponseBody.class);
            return Optional.of(burgerFinderResponseBody.getUrlWithBurger());
        } catch (QminderException e) {
            return Optional.empty();
        }

    }
}
