package gadgetStore.entities;

import gadgetStore.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<Favorite> favorites = new ArrayList<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<Comment> comments = new ArrayList<>();
    @ManyToMany (mappedBy = "products")
    List<Basket> baskets = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product p = (Product) o;
        return id != null && id.equals(p.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
