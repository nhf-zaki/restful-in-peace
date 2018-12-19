package nhfzaki.restfulinpeace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author nhf-zaki on 12/19/18
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadException extends RuntimeException {

    public BadException(String message) {
        super(message);
    }

    public BadException(String message, Throwable cause) {
        super(message, cause);
    }
}
