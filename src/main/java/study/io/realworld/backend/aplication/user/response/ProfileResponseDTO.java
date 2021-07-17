package study.io.realworld.backend.aplication.user.response;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import study.io.realworld.backend.domain.user.Profile;

@Getter
@JsonRootName("profile")
public class ProfileResponseDTO {
    private final String username;
    private final String bio;
    private final String image;
    private final boolean following;

    private ProfileResponseDTO(String username, String bio, String image, boolean following) {
        this.username = username;
        this.bio = bio;
        this.image = image;
        this.following = following;
    }

    public static ProfileResponseDTO of(Profile profile) {
        return new ProfileResponseDTO(profile.getUsername(),
                profile.getBio(),
                profile.getImage(),
                profile.isFollowing());
    }
}
