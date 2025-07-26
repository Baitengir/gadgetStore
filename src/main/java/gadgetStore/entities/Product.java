package gadgetStore.entities;

import gadgetStore.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    double price;
    @ElementCollection
    List<String> images= new ArrayList<>();
    String description;
    boolean isFavorite;
    String madeIn;
    @Enumerated(EnumType.STRING)
    Category category;
    @ManyToOne
    Brand brand;
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    List<Favorite> favorites = new ArrayList<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    List<Comment> comments = new ArrayList<>();
    @ManyToMany (mappedBy = "products")
    List<Basket> baskets = new ArrayList<>();


}
