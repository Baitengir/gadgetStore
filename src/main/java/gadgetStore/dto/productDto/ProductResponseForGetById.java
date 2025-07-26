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
    double price;
    List<String> images;
    String description;
    boolean isFavorite;
    String madeIn;
    Category category;
    Brand brand;
    int likesCount;
    List<Comment> comments;

    public ProductResponseForGetById(Long id, String name, double price,
                                     List<String> images, String description,
                                     boolean isFavorite, String madeIn, Category category,
                                     Brand brand, int likesCount, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.images = images;
        this.description = description;
        this.isFavorite = isFavorite;
        this.madeIn = madeIn;
        this.category = category;
        this.brand = brand;
        this.likesCount = likesCount;
        this.comments = comments;
    }

}
