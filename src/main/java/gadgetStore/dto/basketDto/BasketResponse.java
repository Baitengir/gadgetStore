package gadgetStore.dto.basketDto;

import gadgetStore.dto.productDto.ProductResponseForGetAll;
import gadgetStore.entities.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class BasketResponse {
    Long id;
    List<ProductResponseForGetAll> products;
    double totalPrice;
    int count;
}
