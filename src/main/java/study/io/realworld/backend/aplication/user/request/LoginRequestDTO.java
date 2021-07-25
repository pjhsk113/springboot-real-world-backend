package study.io.realworld.backend.aplication.user.request;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.io.realworld.backend.domain.user.UserLoginModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@JsonRootName("user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequestDTO {
    @Email
    private String email;
    @NotBlank
    private String password;

    public UserLoginModel toLoginRequest() {
        return new UserLoginModel(
                this.email,
                this.password
        );
    }
}
