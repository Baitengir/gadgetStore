package gadgetStore.repository;

import gadgetStore.dto.brandDto.BrandResponse;
import gadgetStore.entities.Brand;
import gadgetStore.exceptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BrandRepo extends JpaRepository<Brand, Long> {

    default Brand getBrandByIdOrIdException (Long id) {
        return findById(id).orElseThrow(
                ()->new NotFoundException("Brand with id '"+id+" ' not found"));
    }

    @Query("""
            select new gadgetStore.dto.brandDto.BrandResponse(
                        g.id, g.name, g.image
                        )
                                    from Brand g
            """)
    List<BrandResponse> getAllBrands();
}
