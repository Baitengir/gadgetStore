package gadgetStore.service.serviceImpl;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.productDto.ProductRequest;
import gadgetStore.dto.productDto.ProductResponseForGetAll;
import gadgetStore.dto.productDto.ProductResponseForGetById;
import gadgetStore.entities.Basket;
import gadgetStore.entities.Brand;
import gadgetStore.entities.Product;
import gadgetStore.enums.Category;
import gadgetStore.repository.BrandRepo;
import gadgetStore.repository.ProductRepo;
import gadgetStore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final BrandRepo brandRepo;

    @Override
    public SimpleResponse save(Category category, Long id, ProductRequest productRequest) { //todo brandId
        Brand brand = brandRepo.getBrandByIdOrIdException(id);

        Product product = Product.builder()
                .name(productRequest.name())
                .price(productRequest.price())
                .description(productRequest.description())
                .images(productRequest.images())
                .madeIn(productRequest.madeIn())
                .category(category)
                .brand(brand)
                .build();

        brand.getProducts().add(product);
        productRepo.save(product);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Product saved successfully")
                .build();
    }

    @Override
    public ProductResponseForGetById getById(Long id) {
        Product product = productRepo.getProductByIdOrException(id);

        return ProductResponseForGetById
                .builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .images(product.getImages())
                .madeIn(product.getMadeIn())
                .category(product.getCategory())
                .brand(product.getBrand())
                .build();
    }

//    @Override
//    public List<ProductResponseForGetAll> getAll() {
//        return productRepo.getAll();
//    }

    @Override
    public SimpleResponse update(Long id, ProductRequest productRequest) {
        Product product = productRepo.getProductByIdOrException(id);

        product.setName(productRequest.name());
        product.setPrice(productRequest.price());
        product.setDescription(productRequest.description());
        product.setImages(productRequest.images());
        product.setMadeIn(productRequest.madeIn());
        productRepo.save(product);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Product updated successfully")
                .build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        Product product = productRepo.getProductByIdOrException(id);
        for (Basket basket : product.getBaskets()) {
            basket.getProducts().remove(product);
        }
        product.getBaskets().clear();
        productRepo.delete(product);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Product deleted successfully")
                .build();
    }

    @Override
    public List<ProductResponseForGetAll> getAllByCategoryAndPrice(Category category, Double price) {
        return productRepo.getAllByCategoryAndPrice(category, price);
    }
}
