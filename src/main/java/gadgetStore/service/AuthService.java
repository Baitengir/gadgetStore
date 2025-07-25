package gadgetStore.service;

import gadgetStore.dto.authDto.AuthResponse;
import gadgetStore.dto.authDto.request.SignInRequest;
import gadgetStore.dto.authDto.request.SignUpRequest;

public interface AuthService {
    AuthResponse signUp(SignUpRequest signUpRequest);
    AuthResponse signIn(SignInRequest signInRequest);
}
