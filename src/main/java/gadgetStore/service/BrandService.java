package gadgetStore.service;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.brandDto.BrandRequest;
import gadgetStore.dto.brandDto.BrandResponse;

import java.util.List;

public interface BrandService {
    SimpleResponse save (BrandRequest brandRequest);
    BrandResponse getById (Long id);
    List<BrandResponse> getAllBrands ();
    SimpleResponse update (Long id, BrandRequest brandRequest);
    SimpleResponse delete (Long id);
}
