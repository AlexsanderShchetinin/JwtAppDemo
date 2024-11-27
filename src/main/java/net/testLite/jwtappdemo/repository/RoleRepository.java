package net.testLite.jwtappdemo.repository;

import net.testLite.jwtappdemo.model.Role;
import net.testLite.jwtappdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
