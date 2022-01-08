package ee.qminder.adapters.venue;

import ee.qminder.config.FourSquareRestConfigService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class FourSquareRestService {

    private final RestTemplate restTemplate;
    private final FourSquareRestConfigService fourSquareRestConfigService;

    public FourSquareRestService(RestTemplateBuilder restTemplateBuilder,
                                 FourSquareRestConfigService fourSquareRestConfigService) {
        this.restTemplate = restTemplateBuilder.build();
        this.fourSquareRestConfigService = fourSquareRestConfigService;
    }

    public ResponseEntity<String> searchVenues(Optional<String> cursor) {
        String urlTemplate = fourSquareRestConfigService.getSearcVenuesUrlTemplate(cursor.isPresent());

        Map<String, String> params = new HashMap<>();
        params.put("near", "tartu");
        params.put("categories", "13000");
        params.put("fields", "fsq_id,name,description,photos");
        params.put("limit", "10");
        cursor.ifPresent(s -> params.put("cursor", s));

        HttpEntity<Void> requestEntity = new HttpEntity<>(fourSquareRestConfigService.getHttpHeader());
        return restTemplate.exchange(urlTemplate, HttpMethod.GET, requestEntity, String.class, params);
    }

}
