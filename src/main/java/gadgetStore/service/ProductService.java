package gadgetStore.service;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.productDto.ProductRequest;
import gadgetStore.dto.productDto.ProductResponseForGetAll;
import gadgetStore.dto.productDto.ProductResponseForGetById;
import gadgetStore.enums.Category;

import java.util.List;

public interface ProductService {
    SimpleResponse save (Long id, ProductRequest productRequest);
    ProductResponseForGetById getById (Long id);
    List<ProductResponseForGetAll> getAll ();
    SimpleResponse update (Long id, ProductRequest productRequest);
    SimpleResponse delete (Long id);
    List<ProductResponseForGetAll> getAllByCategoryAndPrice (Category category, Double price);

}
