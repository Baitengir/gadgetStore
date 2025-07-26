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

    @PostMapping("/{id}")
    public SimpleResponse save (@PathVariable Long id) {
        return favoriteService.save(id);
    }

    @GetMapping("/{id}")
    public FavoriteResponse getById (@PathVariable Long id) {
        return favoriteService.getById(id);
    }
}
