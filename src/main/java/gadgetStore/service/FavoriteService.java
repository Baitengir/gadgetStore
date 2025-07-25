package gadgetStore.service;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.favoriteDto.FavoriteResponse;

import java.util.List;

public interface FavoriteService {
    SimpleResponse save (Long id);
    FavoriteResponse getById (Long id);
    List<FavoriteResponse> getAll ();
    SimpleResponse delete (Long id);
}
