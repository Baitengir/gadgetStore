package gadgetStore.dto.favoriteDto;

import gadgetStore.dto.productDto.ProductResponseForGetById;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FavoriteResponse {
    Long id;
    ProductResponseForGetById product;

    public FavoriteResponse(Long id, ProductResponseForGetById product) {
        this.id = id;
        this.product = product;
    }
}
