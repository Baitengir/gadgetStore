package gadgetStore.dto.productDto;

import gadgetStore.enums.Category;

import java.util.List;

public record ProductRequest (
        String name,
        double price,
        String description,
        List<String> images,
        String madeIn
){
}
