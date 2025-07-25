package gadgetStore.dto.productDto;

import gadgetStore.enums.Category;

import java.util.List;

public record ProductRequest (
        Long id,
        String name,
        String price,
        String description,
        List<String> images,
        String madeIn,
        Category category
){
}
