package gadgetStore.dto.authDto.request;

import gadgetStore.validations.PasswordValid;
import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest

 {
         String firstName;
         String lastName;
         @Email
         String email;
         @PasswordValid
         String password;


}
