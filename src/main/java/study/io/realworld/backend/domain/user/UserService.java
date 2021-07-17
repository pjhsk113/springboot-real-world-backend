package study.io.realworld.backend.domain.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

//    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
//PasswordEncoder passwordEncoder,
    public UserService(UserRepository userRepository) {
//        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User register(UserRegisterModel request) {
//        String encodedPassword = passwordEncoder.encode(request.getPassword());
        return userRepository.save(User.of(request.getUsername(),
                request.getEmail(),
                request.getPassword()));
    }
}
