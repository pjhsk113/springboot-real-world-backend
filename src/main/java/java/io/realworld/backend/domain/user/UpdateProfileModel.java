package java.io.realworld.backend.domain.user;

import lombok.Getter;

@Getter
public class UpdateProfileModel {
    private String username;
    private String email;
    private String password;
    private String bio;
    private String image;

    public UpdateProfileModel(String username, String email, String password, String bio, String image) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.image = image;
    }
}
