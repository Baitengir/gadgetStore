package gadgetStore.service.serviceImpl;

import gadgetStore.config.jwtConfig.JwtService;
import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.userDto.request.UserRequest;
import gadgetStore.dto.userDto.response.UserResponse;
import gadgetStore.entities.Favorite;
import gadgetStore.entities.Product;
import gadgetStore.entities.User;
import gadgetStore.repository.BasketRepo;
import gadgetStore.repository.FavoriteRepo;
import gadgetStore.repository.ProductRepo;
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
    private final ProductRepo productRepo;
    private final FavoriteRepo favoriteRepo;
    private final JwtService jwtService;
    private final BasketRepo basketRepo;

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
                .message("User with id '"+id+"' successfully updated")
                .build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        User user = userRepo.getUserByIdOrException(id);
        userRepo.delete(user);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("User with id '"+ id +"' successfully deleted")
                .build();
    }

    @Override
    public SimpleResponse addProductToFavourite(Long productId) {
        User user = jwtService.getAuthentication();
        Product product = productRepo.getProductByIdOrException(productId);

        Favorite favorite = Favorite.builder()
                .product(product)
                .user(user)
                .build();


        favoriteRepo.save(favorite);
        user.getFavorites().add(favorite);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Product with id '" + productId + "' successfully added to favourite")
                .build();
    }

    @Override
    public SimpleResponse addProductToBasket(Long productId) {
        User user = jwtService.getAuthentication();
        user.getBasket().getProducts().add(productRepo.getProductByIdOrException(productId));
        userRepo.save(user);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Product with id '" + productId + "' successfully added to basket")
                .build();
    }
}
