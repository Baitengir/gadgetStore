package gadgetStore.dto.productDto;

import gadgetStore.entities.Brand;
import gadgetStore.entities.Comment;
import gadgetStore.entities.Favorite;
import gadgetStore.enums.Category;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ProductResponseForGetById {
    Long id;
    String name;
    String price;
    List<String> images;
    String description;
    boolean isFavorite;
    String madeIn;
    Category category;
    Brand brand;
    int likesCount;
    List<Comment> comments;
}
