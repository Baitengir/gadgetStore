package gadgetStore.dto.brandDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BrandResponse {
    Long id;
    String name;
    String image;
}
