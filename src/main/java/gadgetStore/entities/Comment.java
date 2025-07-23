package gadgetStore.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;

@Entity
@Table(name = "comments")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String text;
    ZonedDateTime createdDate;
    @ManyToOne
    User user;
    @ManyToOne
    Product product;
}
