package gadgetStore.api;

import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.commentDto.CommentRequest;
import gadgetStore.dto.commentDto.CommentResponse;
import gadgetStore.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentApi {
    private final CommentService commentService;

    // Добавить комментарий к продукту
    @PostMapping("/{productId}")
    public SimpleResponse addComment(@PathVariable Long productId,
                                     @RequestBody CommentRequest request) {
        return commentService.addComment(productId, request);
    }

    // Получить все комментарии к продукту
    @GetMapping("/product/{productId}")
    public List<CommentResponse> getAllByProductId(@PathVariable Long productId) {
        return commentService.getAllCommentsByProductId(productId);
    }

    // Получить все комментарии пользователя
    @GetMapping("/user/{userId}")
    public List<CommentResponse> getAllByUserId(@PathVariable Long userId) {
        return commentService.getAllCommentsByUserId(userId);
    }

    // Удалить комментарий
    @DeleteMapping("/{commentId}")
    public SimpleResponse delete(@PathVariable Long commentId) {
        return commentService.delete(commentId);
    }
}
