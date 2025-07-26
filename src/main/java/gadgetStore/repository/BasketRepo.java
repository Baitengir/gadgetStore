package gadgetStore.repository;

import gadgetStore.dto.basketDto.BasketResponse;
import gadgetStore.entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepo extends JpaRepository<Basket, Long> {

}
