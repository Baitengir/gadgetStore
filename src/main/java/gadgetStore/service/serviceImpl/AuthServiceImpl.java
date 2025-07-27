package gadgetStore.service.serviceImpl;

import gadgetStore.config.jwtConfig.JwtService;
import gadgetStore.dto.authDto.AuthResponse;
import gadgetStore.dto.authDto.request.SignInRequest;
import gadgetStore.dto.authDto.request.SignUpRequest;
import gadgetStore.entities.Basket;
import gadgetStore.entities.User;
import gadgetStore.enums.Role;
import gadgetStore.exceptions.AlreadyExistException;
import gadgetStore.exceptions.InvalidPasswordException;
import gadgetStore.repository.BasketRepo;
import gadgetStore.repository.UserRepo;
import gadgetStore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final BasketRepo basketRepo;

    @Override
    public AuthResponse signUp(SignUpRequest signUpRequest) {
        Optional<User> userByEmail = userRepo.findUserByEmail(signUpRequest.getEmail());
        if (userByEmail.isPresent()) {
            throw new AlreadyExistException("User with email '" + signUpRequest.getEmail() + "' already exist");
        }

        User user = User.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .signUpDate(ZonedDateTime.now())
                .role(Role.USER)
                .build();


        Basket basket = Basket.builder()
                .user(user)
                .products(new ArrayList<>())
                .build();

        userRepo.save(user);
        basketRepo.save(basket);
        user.setBasket(basket);

        return AuthResponse.builder()
                .id(user.getId())
                .token(jwtService.generateToken(user))
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthResponse signIn(SignInRequest signInRequest) {
        User userInDb = userRepo.getUserByEmailOrException(signInRequest.email());
        if (!passwordEncoder.matches(signInRequest.password(), userInDb.getPassword())) {
            throw new InvalidPasswordException("Invalid password! try again");
        }
        return AuthResponse.builder()
                .id(userInDb.getId())
                .token(jwtService.generateToken(userInDb))
                .role(Role.USER)
                .build();
    }
}
