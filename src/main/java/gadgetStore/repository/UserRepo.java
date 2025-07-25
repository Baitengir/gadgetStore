package gadgetStore.repository;

import gadgetStore.dto.userDto.response.UserResponse;
import gadgetStore.entities.User;
import gadgetStore.exceptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    @Query("""
            select new gadgetStore.dto.userDto.response.UserResponse(
                        u.id, u.firstName, u.lastName, u.email
                        )
            from User u
            """)
    List<UserResponse> getAll();

   default User getUserByEmailOrException(String email){
    return findUserByEmail(email).orElseThrow(
               () -> new NotFoundException("User with email '" + email + "  not found")
       );
   }

    default User getUserByIdOrException(Long id){
        return findById(id).orElseThrow(
                () -> new NotFoundException("User with id '" + id + "' not found")
        );
    }


}
