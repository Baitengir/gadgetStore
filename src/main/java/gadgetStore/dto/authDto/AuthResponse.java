package gadgetStore.dto.authDto;

import gadgetStore.enums.Role;
import lombok.*;

@Builder
@Getter
@Setter
public class AuthResponse {
    Long id;
    String token;
    Role role;
}
