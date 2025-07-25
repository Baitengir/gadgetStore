package gadgetStore.dto.authDto.request;

import jakarta.validation.constraints.Email;

public record SignUpRequest(
        String firstName,
        String lastName,
        @Email
        String email,
        String password
) {
}
