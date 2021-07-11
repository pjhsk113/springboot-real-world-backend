package java.io.realworld.backend.aplication.user.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.realworld.backend.domain.user.UpdateProfileModel;

public class UpdateProfileRequestDTO {
    @NotBlank
    private String username;
    @Email
    private String email;
    @NotBlank
    private String password;
    private String bio;
    private String image;

    UpdateProfileModel toUpdateProfileRequest() {
        return new UpdateProfileModel(
                this.username,
                this.email,
                this.password,
                this.bio,
                this.image
        );
    }
}
