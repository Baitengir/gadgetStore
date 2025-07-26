package gadgetStore.repository;

import gadgetStore.dto.favoriteDto.FavoriteResponse;
import gadgetStore.entities.Favorite;
import gadgetStore.exceptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepo extends JpaRepository<Favorite, Long> {

//    @Query("""
//            select new gadgetStore.dto.favoriteDto.FavoriteResponse(
//
//                        )
//            """)
//    public List<FavoriteResponse> getAll();

    default Favorite getFavoriteByIdOrException(Long id){
        return findById(id).orElseThrow(
                () -> new NotFoundException("Favorite with id '" + id + "' not found")
        );
    }
}
