package java.io.realworld.backend.aplication.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.realworld.backend.aplication.user.request.RegisterRequestDTO;
import java.io.realworld.backend.aplication.user.response.UserResponseDTO;
import java.io.realworld.backend.domain.user.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid RegisterRequestDTO request) {
        return ResponseEntity.ok(
                UserResponseDTO.of(userService.register(request.toRegisterRequest()))
        );
    }

}
