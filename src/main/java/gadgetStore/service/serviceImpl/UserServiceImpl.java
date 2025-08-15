package gadgetStore.service.serviceImpl;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.userDto.request.UserRequest;
import gadgetStore.dto.userDto.response.UserResponse;
import gadgetStore.entities.User;
import gadgetStore.enums.Role;
import gadgetStore.exceptions.ImpossibleException;
import gadgetStore.repository.UserRepo;
import gadgetStore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public UserResponse getById(Long id) {
        User user = userRepo.getUserByIdOrException(id);
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepo.getAll();
    }

    @Override
    public SimpleResponse update(Long id, UserRequest userRequest) {
        User user = userRepo.findById(id).orElseThrow();

        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setUpdatedDate(ZonedDateTime.now());
        userRepo.save(user);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("User with id '" + id + "' successfully updated")
                .build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        User user = userRepo.getUserByIdOrException(id);
        if (user.getRole().equals(Role.ADMIN)){
            throw new ImpossibleException("Impossible delete the admin!");
        }
        userRepo.delete(user);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("User with id '" + id + "' successfully deleted")
                .build();
    }



}
