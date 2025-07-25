package gadgetStore.exceptions;

import jakarta.validation.constraints.Email;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(@Email String s) {
    }
}
