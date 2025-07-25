package gadgetStore.api;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/favorites")
@RequiredArgsConstructor
public class FavoriteApi {
    private final FavoriteService favoriteService;

    @PostMapping("/{id}")
    public SimpleResponse save (@PathVariable Long id) {
        return favoriteService.save(id);
    }
}
