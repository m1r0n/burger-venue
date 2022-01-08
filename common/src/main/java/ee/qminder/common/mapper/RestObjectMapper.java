package ee.qminder.common.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.qminder.common.exception.QminderException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestObjectMapper {

    private final ObjectMapper objectMapper;

    public RestObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T mapResponse(String response, Class<T> mapTo) {
        try {
            return objectMapper.readValue(response, mapTo);
        } catch (JsonProcessingException e) {
            throw new QminderException(e.getMessage(), e);
        }
    }

    public <T> List<T> mapCollection(String response, Class<T> mapTo) {
        try {
            return objectMapper.readValue(response, objectMapper.getTypeFactory().constructCollectionType(List.class, mapTo));
        } catch (JsonProcessingException e) {
            throw new QminderException(e.getMessage(), e);
        }
    }
}
