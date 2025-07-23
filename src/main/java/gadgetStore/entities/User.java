package gadgetStore.entities;

import gadgetStore.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String firstName;
    String lastName;
    String email;
    String password;
    ZonedDateTime signUpDate;
    ZonedDateTime updatedDate;
    @Enumerated(EnumType.STRING)
    Role role;
    @OneToOne (mappedBy = "user")
    Basket basket;
    @OneToMany(mappedBy = "user")
    List<Favorite> favorites;
    @OneToMany(mappedBy = "user")
    List<Comment> comments;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
