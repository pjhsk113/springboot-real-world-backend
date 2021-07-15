package java.io.realworld.backend.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.realworld.backend.domain.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
}
