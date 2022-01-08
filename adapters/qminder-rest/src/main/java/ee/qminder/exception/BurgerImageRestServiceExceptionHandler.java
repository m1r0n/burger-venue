package ee.qminder.exception;

import ee.qminder.common.exception.QminderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class BurgerImageRestServiceExceptionHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                || response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new QminderException("No burger images found");
        } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            throw new QminderException("Retrieving image failed");
        } else {
            throw new QminderException("Request error");
        }
    }
}
