package gadgetStore.service;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.basketDto.BasketResponse;

import java.util.List;

public interface BasketService {
    SimpleResponse cleanBasket();
    BasketResponse getAllProductsInBasket();
}
