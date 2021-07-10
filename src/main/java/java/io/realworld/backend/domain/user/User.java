package java.io.realworld.backend.domain.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Embedded
    private Profile profile;

    @Column(nullable = false)
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private User(String email, Profile profile, String password) {
        this.email = email;
        this.profile = profile;
        this.password = password;
    }

    public static User of(String email, String username, String password) {
        return new User(email, new Profile(username), password);
    }

    void setCurrentTime() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    void updateTime() {
        this.updatedAt = LocalDateTime.now();
    }

}
