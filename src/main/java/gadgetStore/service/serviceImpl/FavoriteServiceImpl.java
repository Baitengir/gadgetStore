package gadgetStore.service.serviceImpl;

import gadgetStore.config.jwtConfig.JwtService;
import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.favoriteDto.FavoriteResponse;
import gadgetStore.entities.Favorite;
import gadgetStore.entities.Product;
import gadgetStore.entities.User;
import gadgetStore.exceptions.NotFoundException;
import gadgetStore.repository.FavoriteRepo;
import gadgetStore.repository.ProductRepo;
import gadgetStore.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final JwtService jwtService;
    private final ProductRepo productRepo;
    private final FavoriteRepo favoriteRepo;

    @Override
    public SimpleResponse save(Long id) { // productId
        Product product = productRepo.getProductByIdOrException(id);

        User user = jwtService.getAuthentication();
        Favorite favorite = Favorite.builder()
                .user(user)
                .product(product)
                .build();
        favoriteRepo.save(favorite);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Product with id " + id + " successfully added to favorite")
                .build();
    }

    @Override
    public FavoriteResponse getById(Long id) {
        return null;
    }

    @Override
    public List<FavoriteResponse> getAll() {
        return List.of();
    }

    @Override
    public SimpleResponse delete(Long id) {
        return null;
    }
}
