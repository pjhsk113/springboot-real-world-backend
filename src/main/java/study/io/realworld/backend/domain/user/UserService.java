package study.io.realworld.backend.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(UserRegisterModel request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        return userRepository.save(User.of(request.getUsername(),
                request.getEmail(),
                encodedPassword));
    }

    public User login(UserLoginModel request) {
        return userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("email not found"));
    }
}
