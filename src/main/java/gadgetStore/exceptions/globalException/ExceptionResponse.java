package gadgetStore.exceptions.globalException;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionResponse {
    private String message;
    private HttpStatus status;

    public ExceptionResponse(String message, HttpStatus status){
        this.message = message;
        this.status = status;
    }
}
