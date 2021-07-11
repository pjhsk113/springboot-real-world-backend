package java.io.realworld.backend.aplication.user.request;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.realworld.backend.domain.user.UserRegisterModel;

@JsonRootName("user")
public class RegisterRequestDTO {
    @NotBlank
    private String username;

    @Email
    private String email;

    @NotBlank
    private String password;

    UserRegisterModel toRegisterRequest() {
        return new UserRegisterModel(
                this.username,
                this.email,
                this.password);
    }
}
