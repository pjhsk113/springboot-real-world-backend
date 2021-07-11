package java.io.realworld.backend.aplication.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.realworld.backend.aplication.user.request.RegisterRequestDTO;
import java.io.realworld.backend.aplication.user.response.UserResponseDTO;

@RestController
@RequestMapping("/api")
public class UserController {

    @PostMapping("/users")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid RegisterRequestDTO request) {
        return null;
    }

}
