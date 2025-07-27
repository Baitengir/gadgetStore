package gadgetStore.service;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.favoriteDto.FavoriteResponse;

import java.util.List;

public interface FavoriteService {
    SimpleResponse addProduct(Long productId);
    List<FavoriteResponse> getAllFavoritesByUserId (Long id);
    FavoriteResponse getById (Long id);
    SimpleResponse delete (Long id);

}
