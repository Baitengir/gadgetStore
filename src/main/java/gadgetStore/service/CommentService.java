package gadgetStore.service;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.commentDto.CommentRequest;
import gadgetStore.dto.commentDto.CommentResponse;

import java.util.List;

public interface CommentService {
    SimpleResponse addComment(Long id, CommentRequest commentRequest); // productId

    List<CommentResponse> getAllCommentsByProductId(Long id);

    List<CommentResponse> getAllCommentsByUserId(Long id);

    SimpleResponse delete(Long id);

}
