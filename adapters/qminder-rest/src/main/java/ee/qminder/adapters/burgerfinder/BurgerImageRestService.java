package ee.qminder.adapters.burgerfinder;

import ee.qminder.config.BurgerImageRestConfigService;
import ee.qminder.entity.BurgerFinderInput;
import ee.qminder.exception.BurgerImageRestServiceExceptionHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BurgerImageRestService {

    private final RestTemplate restTemplate;
    private final BurgerImageRestConfigService burgerImageRestConfigService;

    public BurgerImageRestService(RestTemplateBuilder restTemplateBuilder, BurgerImageRestConfigService burgerImageRestConfigService) {
        this.restTemplate = restTemplateBuilder.build();
        this.restTemplate.setErrorHandler(new BurgerImageRestServiceExceptionHandler());
        this.burgerImageRestConfigService = burgerImageRestConfigService;
    }

    public ResponseEntity<String> getUrlsWithBurger(BurgerFinderInput burgerFinderInput) {
        String urlTemplate = burgerImageRestConfigService.getUrlTemplate();

        HttpEntity<BurgerFinderInput> requestEntity = new HttpEntity<>(burgerFinderInput);
        return restTemplate.exchange(urlTemplate, HttpMethod.POST, requestEntity, String.class);
    }
}
