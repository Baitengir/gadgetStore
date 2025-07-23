package gadgetStore.dto.authDto.request;

public record SignInRequest(
        String email,
        String password
) {
}
