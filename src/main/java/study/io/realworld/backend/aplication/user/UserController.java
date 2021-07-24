package study.io.realworld.backend.aplication.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import study.io.realworld.backend.aplication.user.request.LoginRequestDTO;
import study.io.realworld.backend.aplication.user.request.RegisterRequestDTO;
import study.io.realworld.backend.aplication.user.response.UserResponseDTO;
import study.io.realworld.backend.domain.user.User;
import study.io.realworld.backend.domain.user.UserService;
import study.io.realworld.infra.security.TokenGenerator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final TokenGenerator tokenGenerator;

    @PostMapping("/users")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid RegisterRequestDTO request) {
        return ResponseEntity.ok(
                UserResponseDTO.of(userService.register(request.toRegisterRequest()))
        );
    }

    @PostMapping("/users/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody @Valid LoginRequestDTO request) {
        User user = userService.login(request.toLoginRequest());
        String token = tokenGenerator.generateToken(user.getEmail());

        return ResponseEntity.ok(UserResponseDTO.of(user, token));
    }
}
