package gadgetStore.api;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.userDto.request.UserRequest;
import gadgetStore.dto.userDto.response.UserResponse;
import gadgetStore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class UserApi {
    private final UserService userService;


    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping
    public List<UserResponse> getAll() {
        return userService.getAll();
    }

    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.update(id, userRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public SimpleResponse delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @PostMapping("/basket/{productId}")
    public SimpleResponse addProductToBasket(@PathVariable Long productId) {
        return userService.addProductToBasket(productId);
    }

    @PostMapping("/favourite/{productId}")
    public SimpleResponse addProductToFavourite(@PathVariable Long productId) {
        return userService.addProductToFavourite(productId);
    }

}
