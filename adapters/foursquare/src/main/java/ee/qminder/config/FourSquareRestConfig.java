package ee.qminder.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("foursquare.properties")
public class FourSquareRestConfig {

    @Value("${authorization.token}")
    private String token;

    @Bean
    public FourSquareRestConfigService fourSquareRestConfigService() {
        return new FourSquareRestConfigService(token);
    }

}
