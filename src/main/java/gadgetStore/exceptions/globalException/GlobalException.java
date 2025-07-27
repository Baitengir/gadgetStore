package gadgetStore.exceptions.globalException;

import gadgetStore.exceptions.AlreadyExistException;
import gadgetStore.exceptions.ImpossibleException;
import gadgetStore.exceptions.InvalidPasswordException;
import gadgetStore.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse getExceptionResponse(NotFoundException notFoundException) {
        return new ExceptionResponse (
                notFoundException.getMessage(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse getExceptionResponse(AlreadyExistException alreadyExistException) {
        return new ExceptionResponse (
                alreadyExistException.getMessage(),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse getExceptionResponse(InvalidPasswordException invalidPasswordException) {
        return new ExceptionResponse (
                invalidPasswordException.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImpossibleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse getExceptionResponse(ImpossibleException impossibleException) {
        return new ExceptionResponse (
                impossibleException.getMessage(),
                HttpStatus.BAD_REQUEST);
    }


}
