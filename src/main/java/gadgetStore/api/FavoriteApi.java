package gadgetStore.api;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.favoriteDto.FavoriteResponse;
import gadgetStore.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/favorites")
@RequiredArgsConstructor
public class FavoriteApi {
    private final FavoriteService favoriteService;

    @PostMapping("/favourite/{productId}")
    public SimpleResponse addProductToFavourite(@PathVariable Long productId) {
        return favoriteService.addProduct(productId);
    }

    @GetMapping("/{id}")
    public FavoriteResponse getById (@PathVariable Long id) {
        return favoriteService.getById(id);
    }
}
