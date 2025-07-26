package gadgetStore.dto.basketDto;

import java.util.List;

public record BasketRequest (
        List<Long> productsIds
){
}
