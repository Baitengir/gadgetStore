package gadgetStore.service.serviceImpl;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.brandDto.BrandRequest;
import gadgetStore.dto.brandDto.BrandResponse;
import gadgetStore.entities.Basket;
import gadgetStore.entities.Brand;
import gadgetStore.entities.Product;
import gadgetStore.repository.BrandRepo;
import gadgetStore.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepo brandRepo;

    @Override
    public SimpleResponse save(BrandRequest brandRequest) {
        brandRepo.save(
                Brand.builder()
                        .name(brandRequest.name())
                        .image(brandRequest.image())
                        .build()
        );
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Brand successfully saved")
                .build();
    }

    @Override
    public BrandResponse getById(Long id) {
        Brand brand = brandRepo.getBrandByIdOrIdException(id);
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .image(brand.getImage())
                .build();
    }

    @Override
    public List<BrandResponse> getAllBrands() {
        return brandRepo.getAllBrands();
    }

    @Override
    public SimpleResponse update(Long id, BrandRequest brandRequest) {
        Brand brand = brandRepo.getBrandByIdOrIdException(id);

        brand.setName(brandRequest.name());
        brand.setImage(brandRequest.image());
        brandRepo.save(brand);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Brand successfully updated")
                .build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        Brand brand = brandRepo.getBrandByIdOrIdException(id);
        for (Product product : brand.getProducts()) {
            for (Basket basket : product.getBaskets()) {
                basket.getProducts().remove(product);
            }
        }

        brandRepo.delete(brand);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Brand successfully deleted")
                .build();
    }
}
