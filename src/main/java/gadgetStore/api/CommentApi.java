package gadgetStore.api;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.commentDto.CommentRequest;
import gadgetStore.dto.commentDto.CommentResponse;
import gadgetStore.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentApi {
    private final CommentService commentService;

    @PostMapping("/{productId}")
    public SimpleResponse addComment(@PathVariable Long productId,
                                     @RequestBody CommentRequest request) {
        return commentService.addComment(productId, request);
    }

    @GetMapping("/product/{productId}")
    public List<CommentResponse> getAllByProductId(@PathVariable Long productId) {
        return commentService.getAllCommentsByProductId(productId);
    }

    @GetMapping("/user/{userId}")
    public List<CommentResponse> getAllByUserId(@PathVariable Long userId) {
        return commentService.getAllCommentsByUserId(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{commentId}")
    public SimpleResponse delete(@PathVariable Long commentId) {
        return commentService.delete(commentId);
    }
}
