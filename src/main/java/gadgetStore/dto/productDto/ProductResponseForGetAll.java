package gadgetStore.dto.productDto;

import gadgetStore.enums.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductResponseForGetAll {
    Long id;
    String name;
    double price;
    String madeIn;
    Category category;

    public ProductResponseForGetAll(Long id, String name, double price, String madeIn, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.madeIn = madeIn;
        this.category = category;
    }



}
