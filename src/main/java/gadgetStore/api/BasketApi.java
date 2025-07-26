package gadgetStore.api;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.basketDto.BasketResponse;
import gadgetStore.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/baskets")
@RequiredArgsConstructor
public class BasketApi {
    private final BasketService basketService;

    @DeleteMapping("cleanBasket")
    public SimpleResponse cleanBasket() {
        return basketService.cleanBasket();
    }

    @GetMapping("AllProducts")
    public BasketResponse getAllProductsInBasket() {
        return basketService.getAllProductsInBasket();
    }
}
