package gadgetStore.api;

import gadgetStore.dto.authDto.AuthResponse;
import gadgetStore.dto.authDto.request.SignInRequest;
import gadgetStore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;

    @PostMapping
    public AuthResponse signIn(@RequestBody SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }
}
