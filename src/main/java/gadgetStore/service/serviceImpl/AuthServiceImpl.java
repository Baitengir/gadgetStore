package gadgetStore.service.serviceImpl;

import gadgetStore.config.jwtConfig.JwtService;
import gadgetStore.dto.authDto.AuthResponse;
import gadgetStore.dto.authDto.request.SignInRequest;
import gadgetStore.dto.authDto.request.SignUpRequest;
import gadgetStore.entities.User;
import gadgetStore.enums.Role;
import gadgetStore.exceptions.AlreadyExistException;
import gadgetStore.repository.UserRepo;
import gadgetStore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse signUp(SignUpRequest signUpRequest) {

        Optional<User> userByEmail = userRepo.findUserByEmail(signUpRequest.email());

        if (userByEmail.isPresent()) {
            throw new AlreadyExistException("User with email '" + signUpRequest.email() + "' already exist");
        }

        User user = User.builder()
                .firstName(signUpRequest.firstName())
                .lastName(signUpRequest.lastName())
                .email(signUpRequest.email())
                .password(passwordEncoder.encode(signUpRequest.password()))
                .signUpDate(ZonedDateTime.now())
                .role(Role.USER)
                .build();

        userRepo.save(user);

        return   AuthResponse.builder()
                .id(user.getId())
                .token(jwtService.generateToken(user))
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthResponse signIn(SignInRequest signInRequest) {

        User userInDb = userRepo.getUserByEmailOrException(signInRequest.email());

        if (!passwordEncoder.matches(signInRequest.password(), userInDb.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return AuthResponse.builder()
                .id(userInDb.getId())
                .token(jwtService.generateToken(userInDb))
                .role(Role.USER)
                .build();
    }
}
