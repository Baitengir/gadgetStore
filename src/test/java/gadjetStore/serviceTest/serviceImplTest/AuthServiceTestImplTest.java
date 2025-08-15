package gadjetStore.serviceTest.serviceImplTest;

import gadgetStore.config.jwtConfig.JwtService;
import gadgetStore.dto.authDto.request.SignInRequest;
import gadgetStore.entities.User;
import gadgetStore.repository.UserRepo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTestImplTest {
    @Mock
    private UserRepo userRepo;
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private AuthServiceTestImplTest authService;

    public void signIn_validCredentials_shouldReturnAuthResponse (){
        SignInRequest signInRequest = new SignInRequest("testEmail", "testPassword");
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setEmail("test@email");
        mockUser.setPassword("encodedPassword");

        when(userRepo.getUserByEmailOrException("testEmail")).thenReturn(mockUser);
        when(passwordEncoder.matches("testPassword", mockUser.getPassword())).thenReturn(true);
        when(jwtService.generateToken(mockUser)).thenReturn("testToken");

    }
}
