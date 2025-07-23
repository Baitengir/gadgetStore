package gadgetStore.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SimpleResponse {
    HttpStatus httpStatus;
    String message;
}
