package gadgetStore.service.serviceImpl;

import gadgetStore.config.jwtConfig.JwtService;
import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.commentDto.CommentRequest;
import gadgetStore.dto.commentDto.CommentResponse;
import gadgetStore.entities.Comment;
import gadgetStore.entities.Product;
import gadgetStore.entities.User;
import gadgetStore.repository.CommentRepo;
import gadgetStore.repository.ProductRepo;
import gadgetStore.repository.UserRepo;
import gadgetStore.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;
    private final ProductRepo productRepo;
    private final JwtService jwtService;
    private final UserRepo userRepo;

    @Override
    public SimpleResponse addComment(Long id, CommentRequest commentRequest) {
        User user = jwtService.getAuthentication();
        Product product = productRepo.getProductByIdOrException(id);
        Comment comment = Comment.builder()
                .user(user)
                .product(product)
                .text(commentRequest.comment())
                .createdDate(ZonedDateTime.now())
                .build();

        commentRepo.save(comment);
        product.getComments().add(comment);
        user.getComments().add(comment);
        // todo productRepo.save(product); and userRepo.save(user);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Comment successfully added")
                .build();
    }

    @Override
    public List<CommentResponse> getAllCommentsByProductId(Long id) {
        return commentRepo.getAllCommentsByProduct(productRepo.getProductByIdOrException(id));
    }

    @Override
    public List<CommentResponse> getAllCommentsByUserId(Long id) {
        return commentRepo.getAllCommentsByUser(userRepo.getUserByIdOrException(id));
    }

    @Override
    public SimpleResponse delete(Long id) {
        Comment comment = commentRepo.getCommentByIdOrException(id);
        commentRepo.delete(comment);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Comment successfully deleted")
                .build();
    }
}
