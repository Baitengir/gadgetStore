package gadgetStore.repository;

import gadgetStore.dto.favoriteDto.FavoriteResponse;
import gadgetStore.entities.Favorite;
import gadgetStore.entities.Product;
import gadgetStore.entities.User;
import gadgetStore.exceptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepo extends JpaRepository<Favorite, Long> {

    @Query("SELECT f FROM Favorite f WHERE f.user.id = :userId AND f.product.id = :productId")
    Optional<Favorite> getByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    List<FavoriteResponse> findAllByUserId(Long userId);

    default Favorite getFavoriteByIdOrException(Long id){
        return findById(id).orElseThrow(
                () -> new NotFoundException("Favorite with id '" + id + "' not found")
        );
    }


}
