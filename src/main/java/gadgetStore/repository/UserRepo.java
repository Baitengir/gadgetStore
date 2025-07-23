package gadgetStore.repository;

import gadgetStore.entities.User;
import gadgetStore.exceptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

   default User findUserByEmailException(String email){
    return findUserByEmail(email).orElseThrow(
               () -> new NotFoundException("User with email '" + email + "  not found")
       );
   }
}
