package gadgetStore.dto.commentDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
public class CommentResponse {
    Long id;
    String comment;
    ZonedDateTime createdDate;
}
