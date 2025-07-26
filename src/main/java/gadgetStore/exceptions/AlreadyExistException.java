package gadgetStore.exceptions;
import jakarta.validation.constraints.Email;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(String message) {
        super(message);
    }
}
