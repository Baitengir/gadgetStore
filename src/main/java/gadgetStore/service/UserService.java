package gadgetStore.service;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.userDto.request.UserRequest;
import gadgetStore.dto.userDto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getById(Long id);
    List<UserResponse> getAll();
    SimpleResponse update(Long id, UserRequest userRequest);
    SimpleResponse delete(Long id);
    SimpleResponse addProductToFavourite(Long productId);
    SimpleResponse addProductToBasket(Long productId);
    //addProductToFavorite
}
