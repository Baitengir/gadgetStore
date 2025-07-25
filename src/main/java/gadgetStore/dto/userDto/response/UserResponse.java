package gadgetStore.dto.userDto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
    Long id;
    String firstName;
    String lastName;
    String email;

}
