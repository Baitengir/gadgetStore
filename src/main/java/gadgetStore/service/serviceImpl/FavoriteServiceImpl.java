package gadgetStore.service.serviceImpl;

import gadgetStore.config.jwtConfig.JwtService;
import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.favoriteDto.FavoriteResponse;
import gadgetStore.dto.productDto.ProductResponseForGetById;
import gadgetStore.entities.Favorite;
import gadgetStore.entities.Product;
import gadgetStore.entities.User;
import gadgetStore.repository.FavoriteRepo;
import gadgetStore.repository.ProductRepo;
import gadgetStore.repository.UserRepo;
import gadgetStore.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final JwtService jwtService;
    private final ProductRepo productRepo;
    private final FavoriteRepo favoriteRepo;
    private final UserRepo userRepo;

    @Override
    public SimpleResponse addProduct(Long id) { // productId

        User user = jwtService.getAuthentication();
        Product product = productRepo.getProductByIdOrException(id);

        Optional<Favorite> favInDb = favoriteRepo.getByUserIdAndProductId(user.getId(), product.getId());

        if (favInDb.isPresent()) {
            Favorite favorite = favInDb.get();

            user.getFavorites().remove(favorite);
            product.getFavorites().remove(favorite);
            product.setFavorite(false);
            favoriteRepo.delete(favorite);

            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Product with id '" + id + "' removed from favourites")
                    .build();
        }

        Favorite favorite = Favorite.builder()
                .user(user)
                .product(product)
                .build();

        user.getFavorites().add(favorite);
        product.getFavorites().add(favorite);
        product.setFavorite(true);
        favoriteRepo.save(favorite);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Product with id '" + id + "' added to favourites")
                .build();
    }

    @Override
    public List<FavoriteResponse> getAllFavoritesByUserId(Long id) {
        User user = userRepo.getUserByIdOrException(id);
        return favoriteRepo.findAllByUserId(user.getId());
    }

    @Override
    public FavoriteResponse getById(Long id) {
        Favorite favorite = favoriteRepo.getFavoriteByIdOrException(id);
        Product product = favorite.getProduct();

        ProductResponseForGetById productResponse =
                ProductResponseForGetById
                        .builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .images(product.getImages())
                        .madeIn(product.getMadeIn())
                        .category(product.getCategory())
                        .brand(product.getBrand())
                        .build();

        return FavoriteResponse.builder()
                .id(favorite.getId())
                .product(productResponse)
                .build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        Favorite favorite = favoriteRepo.getFavoriteByIdOrException(id);
        favoriteRepo.delete(favorite);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Favorite with id '" + id + "' successfully deleted")
                .build();
    }
}
