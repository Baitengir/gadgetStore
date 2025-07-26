package gadgetStore.dto.favoriteDto;

import gadgetStore.dto.productDto.ProductResponseForGetById;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FavoriteResponse {
    Long id;
    ProductResponseForGetById product;
}
