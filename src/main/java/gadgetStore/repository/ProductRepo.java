package gadgetStore.repository;

import gadgetStore.dto.productDto.ProductResponseForGetAll;
import gadgetStore.entities.Product;
import gadgetStore.enums.Category;
import gadgetStore.exceptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
//    Long id;
//    String name;
//    String price;
//    String madeIn;
//    Category category;
//    Brand brand;


    @Query("""
            select new gadgetStore.dto.productDto.ProductResponseForGetAll(
                        p.id, p.name, p.price, p.madeIn, p.category
                        )
            from Product p
                        where p.category = :category
                                    and p.price <= :price
            """)
    List<ProductResponseForGetAll> getAllByCategoryAndPrice(Category category, double price);

    default Product getProductByIdOrException(Long id){
        return findById(id).orElseThrow(
                () -> new NotFoundException("Product with id " + id + " not found!"));
    }
}
