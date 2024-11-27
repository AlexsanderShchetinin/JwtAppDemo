package net.testLite.jwtappdemo.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.testLite.jwtappdemo.model.Role;
import net.testLite.jwtappdemo.model.StatusEntity;
import net.testLite.jwtappdemo.model.User;
import net.testLite.jwtappdemo.repository.RoleRepository;
import net.testLite.jwtappdemo.repository.UserRepository;
import net.testLite.jwtappdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(StatusEntity.ACTIVE);

        User registedUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registedUser);

        return registedUser;
    }

    @Override
    public List<User> getAll() {
        List<User> all = userRepository.findAll();
        log.info("IN getAll - {} users found", all.size());
        return all;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if(user == null){
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", user, id);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", user, username);
        return user;
    }
}
