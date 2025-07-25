package gadgetStore.dto.productDto;

import gadgetStore.entities.Brand;
import gadgetStore.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductResponseForGetAll {
    Long id;
    String name;
    String price;
    String madeIn;
    Category category;
    Brand brand;
}
