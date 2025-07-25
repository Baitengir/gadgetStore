package gadgetStore.api;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.brandDto.BrandRequest;
import gadgetStore.dto.brandDto.BrandResponse;
import gadgetStore.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/brands")
@RequiredArgsConstructor
public class BrandApi {
    private final BrandService brandService;

    @PostMapping
    public SimpleResponse save(@RequestBody BrandRequest brandRequest) {
        return brandService.save(brandRequest);
    }

    @GetMapping("/{id}")
    public BrandResponse getById(@PathVariable Long id) {
        return brandService.getById(id);
    }

    @GetMapping
    public List<BrandResponse> getAllBrands() {
        return brandService.getAllBrands();
    }

    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id,
                                 @RequestBody BrandRequest brandRequest) {
        return brandService.update(id, brandRequest);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id) {
        return brandService.delete(id);
    }
}
