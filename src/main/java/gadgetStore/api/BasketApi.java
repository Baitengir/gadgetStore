package gadgetStore.api;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.basketDto.BasketResponse;
import gadgetStore.service.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/baskets")
@RequiredArgsConstructor
public class BasketApi {
    private final BasketService basketService;

    @PostMapping("/basket/{productId}")
    @Operation(summary = "Add product to basket", description = "Add product to basket")
    public SimpleResponse addProductToBasket(@PathVariable Long productId) {
        return basketService.addProduct(productId);
    }

    @DeleteMapping("cleanBasket")
    public SimpleResponse cleanBasket() {
        return basketService.cleanBasket();
    }

    @GetMapping("AllProducts")
    public BasketResponse getAllProductsInBasket() {
        return basketService.getAllProductsInBasket();
    }
}
