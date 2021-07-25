package study.io.realworld.backend.aplication.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import study.io.realworld.backend.aplication.user.request.LoginRequestDTO;
import study.io.realworld.backend.aplication.user.request.RegisterRequestDTO;
import study.io.realworld.backend.domain.user.UserService;
import study.io.realworld.infra.security.TokenGenerator;

import java.util.stream.Stream;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private TokenGenerator tokenGenerator;

    @MockBean
    private UserService userService;

    private String testToken;

    @BeforeEach
    private void setUp() {
        testToken = "Token " + tokenGenerator.generateToken("user@email.com");
    }

    @MethodSource("invalidRegisterDto")
    @ParameterizedTest
    void when_register_user_invalid_body(RegisterRequestDTO dto) throws Exception {
        mockMvc.perform(post("/api/users")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void when_user_login_expect_is_ok() throws Exception {
        LoginRequestDTO requestDTO = new LoginRequestDTO("user@email.com", "password");

        mockMvc.perform(post("/api/users/login")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.token").value("user@email.com"));
    }

    private static Stream<Arguments> invalidRegisterDto() {
        return Stream.of(
                Arguments.of(new RegisterRequestDTO("userName", "not.email.com", "password")),
                Arguments.of(new RegisterRequestDTO("", "user@email.com", "password")),
                Arguments.of(new RegisterRequestDTO("userName", "user@email.com", ""))
        );
    }
}