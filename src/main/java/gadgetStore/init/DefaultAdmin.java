package gadgetStore.init;

import gadgetStore.entities.User;
import gadgetStore.enums.Role;
import gadgetStore.repository.UserRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DefaultAdmin {
    private final UserRepo userRepo;

    @PostConstruct
    public void saveAdmin (){
        Optional<User> user = userRepo.findUserByEmail("admin@gmail.com");

        if (user.isEmpty()) {
            userRepo.save(User.builder()
                    .firstName("admin")
                    .lastName("adminov")
                    .email("admin@gmail.com")
                    .password("admin123")
                    .role(Role.ADMIN)
                    .build());
        }

    }

}
