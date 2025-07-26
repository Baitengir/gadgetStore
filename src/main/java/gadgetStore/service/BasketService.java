package gadgetStore.service;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.basketDto.BasketResponse;

public interface BasketService {
    SimpleResponse cleanBasket();
    BasketResponse getAllProductsInBasket();
}
