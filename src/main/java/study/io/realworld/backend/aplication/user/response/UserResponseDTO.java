package study.io.realworld.backend.aplication.user.response;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import study.io.realworld.backend.domain.user.User;

@Getter
@JsonRootName("user")
public class UserResponseDTO {
    private final String email;
    private final String token;
    private final String username;
    private final String bio;
    private final String image;

    private UserResponseDTO(String email, String token, String username, String bio, String image) {
        this.email = email;
        this.token = token;
        this.username = username;
        this.bio = bio;
        this.image = image;
    }

    public static UserResponseDTO of(User user) {
        return new UserResponseDTO(user.getEmail(), "",
                user.getProfile().getUsername(),
                user.getProfile().getBio(),
                user.getProfile().getImage());
    }

    public static UserResponseDTO of(User user, String token) {
        return new UserResponseDTO(user.getEmail(), token,
                user.getProfile().getUsername(),
                user.getProfile().getBio(),
                user.getProfile().getImage());
    }
}
