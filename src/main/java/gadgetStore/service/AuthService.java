package gadgetStore.service;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.authDto.AuthResponse;
import gadgetStore.dto.authDto.request.SignInRequest;
import gadgetStore.dto.authDto.request.SignUpRequest;

public interface AuthService {
    SimpleResponse signUp(SignUpRequest signUpRequest);
    AuthResponse signIn(SignInRequest signInRequest);
}
