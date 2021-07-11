package java.io.realworld.backend.domain.user;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Profile {
    @Column(nullable = false, unique = true)
    private String username;
    private String bio;
    private String image;
    private boolean following;

    private Profile(String username, String bio, String image, boolean following) {
        this.username = username;
        this.bio = bio;
        this.image = image;
        this.following = following;
    }

    public Profile(String username) {
        this(username, null, null, false);
    }

    protected Profile() { }

}
