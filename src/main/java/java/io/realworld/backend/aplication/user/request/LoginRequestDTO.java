package java.io.realworld.backend.aplication.user.request;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.realworld.backend.domain.user.UserLoginModel;

@JsonRootName("user")
public class LoginRequestDTO {
    @Email
    private String email;
    @NotBlank
    private String password;

    UserLoginModel toLoginRequest() {
        return new UserLoginModel(
                this.email,
                this.password
        );
    }
}
