package ee.qminder.config;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class BurgerImageRestConfigService {

    private static final String BASE_URL = "https://pplkdijj76.execute-api.eu-west-1.amazonaws.com/prod/recognize";

    public String getUrlTemplate() {
        return UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .encode()
                .toUriString();
    }
}
