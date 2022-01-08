package ee.qminder.common.exception;

public class QminderException extends RuntimeException {

    public QminderException(String message) {
        super(message);
    }

    public QminderException(String message, Throwable cause) {
        super(message, cause);
    }
}
