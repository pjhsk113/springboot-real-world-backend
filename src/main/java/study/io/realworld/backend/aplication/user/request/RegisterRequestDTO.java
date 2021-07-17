package study.io.realworld.backend.aplication.user.request;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import study.io.realworld.backend.domain.user.UserRegisterModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@JsonRootName("user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequestDTO {
    @NotBlank
    private String username;

    @Email
    private String email;

    @NotBlank
    private String password;

    public UserRegisterModel toRegisterRequest() {
        return new UserRegisterModel(
                this.username,
                this.email,
                this.password);
    }
}
