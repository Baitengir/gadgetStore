package gadgetStore.service;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.basketDto.BasketResponse;

public interface BasketService {
    SimpleResponse addProduct(Long id);
    SimpleResponse cleanBasket();
    BasketResponse getAllProductsInBasket();
}
