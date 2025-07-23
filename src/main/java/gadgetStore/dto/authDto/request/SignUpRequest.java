package gadgetStore.dto.authDto.request;

public record SignUpRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
