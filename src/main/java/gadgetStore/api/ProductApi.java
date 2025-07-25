package gadgetStore.api;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.productDto.ProductRequest;
import gadgetStore.dto.productDto.ProductResponseForGetAll;
import gadgetStore.dto.productDto.ProductResponseForGetById;
import gadgetStore.enums.Category;
import gadgetStore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductApi {
    private final ProductService productService;

    @PostMapping("/{id}")
    public SimpleResponse save(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return productService.save(id, productRequest);
    }

    @GetMapping("/{id}")
    public ProductResponseForGetById getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping
    public List<ProductResponseForGetAll> getAll() {
        return productService.getAll();
    }

    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id,
                                 @RequestBody ProductRequest productRequest) {
        return productService.update(id, productRequest);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id) {
        return productService.delete(id);
    }

    @GetMapping("/filter")
    public List<ProductResponseForGetAll> getAllByCategoryAndPrice(
            @RequestParam Category category,
            @RequestParam Double price
    ) {
        return productService.getAllByCategoryAndPrice(category, price);
    }
}
