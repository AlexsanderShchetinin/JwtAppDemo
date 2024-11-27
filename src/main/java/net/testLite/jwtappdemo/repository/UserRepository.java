package net.testLite.jwtappdemo.repository;

import net.testLite.jwtappdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
