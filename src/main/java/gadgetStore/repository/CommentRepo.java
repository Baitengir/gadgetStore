package gadgetStore.repository;

import gadgetStore.dto.commentDto.CommentResponse;
import gadgetStore.entities.Comment;
import gadgetStore.entities.Product;
import gadgetStore.entities.User;
import gadgetStore.exceptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    @Query("""
                        select new gadgetStore.dto.commentDto.CommentResponse(
            c.id, c.text, c.createdDate
                        )
                                    from Comment c
                                                where c.product = :product
            """)
    List<CommentResponse> getAllCommentsByProduct(Product product);

    @Query("""
            select new gadgetStore.dto.commentDto.CommentResponse(
            c.id, c.text, c.createdDate
                        )
                                    from Comment c
                                                where c.user = :user
            """)
    List<CommentResponse> getAllCommentsByUser(User user);

    default Comment getCommentByIdOrException(Long id){
        return findById(id).orElseThrow(
                () -> new NotFoundException("Comment with id '" + id + "' not found")
        );
    }

}
