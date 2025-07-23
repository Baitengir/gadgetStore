package gadgetStore.service.serviceImpl;

import gadgetStore.config.jwtConfig.JwtService;
import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.authDto.AuthResponse;
import gadgetStore.dto.authDto.request.SignInRequest;
import gadgetStore.dto.authDto.request.SignUpRequest;
import gadgetStore.entities.User;
import gadgetStore.enums.Role;
import gadgetStore.exceptions.AlreadyExistException;
import gadgetStore.exceptions.NotFoundException;
import gadgetStore.repository.UserRepo;
import gadgetStore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public SimpleResponse signUp(SignUpRequest signUpRequest) {
        User user = User.builder()
                .firstName(signUpRequest.firstName())
                .lastName(signUpRequest.lastName())
                .email(signUpRequest.email())
                .password(passwordEncoder.encode(signUpRequest.password()))
                .signUpDate(ZonedDateTime.now())
                .build();

        Optional<User> userByEmail = userRepo.findUserByEmail(user.getEmail());
        if (userByEmail.isEmpty()) {
            userRepo.save(user);
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.CREATED)
                    .message("User registered successfully")
                    .build();
        }

        throw new AlreadyExistException("User with email " + signUpRequest.email() + " already exist in database");
    }

    @Override
    public AuthResponse signIn(SignInRequest signInRequest) {

        User userInDb = userRepo.findUserByEmailException(signInRequest.email());

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
