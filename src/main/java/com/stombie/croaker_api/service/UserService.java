package com.stombie.croaker_api.service;

import com.stombie.croaker_api.exception.UserAlreadyExistsException;
import com.stombie.croaker_api.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stombie.croaker_api.entity.Role;
import com.stombie.croaker_api.entity.User;
import com.stombie.croaker_api.repo.RoleRepository;
import com.stombie.croaker_api.repo.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public Boolean isFollowedTo(User checker, Long userId) throws UserNotFoundException {
        if (checker == null) {
            throw new NullPointerException("Argument 'checker' is null");
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            log.error("User with id '{}' not found", userId);
            throw new UserNotFoundException(String.format("User with id '%d' not found", userId));
        }
        return userRepository.isFollowedByCheckerIdAndUserId(checker.getId(), userId);
    }

    public User saveUser(User user) throws UserAlreadyExistsException {
        User existingUser = findUser(user.getUsername());
        if (existingUser != null) {
            log.error("Cannot save user with username '{}' because it already exists", user.getUsername());
            throw new UserAlreadyExistsException(String.format("User with username '%s' already exists", user.getUsername()));
        }
        user = userRepository.save(user);
        log.info("User with username '{}' saved successfully", user.getUsername());
        return user;
    }

    public Role saveRole(Role role) {
        log.info("Successfully saved role with name '{}'", role.getName());
        return roleRepository.save(role);
    }

    public void addRoleToUser(String roleName, String username) {
        Role role = roleRepository.findRoleByName(roleName);
        if (role == null) {
            log.error("Failed to add role to user: role with name '{}' was not found", roleName);
            return;
        }
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            log.error("Failed to add role to user: user with username '{}' was not found", username);
            return;
        }
        user.getRoles().add(role);
        log.info("Successfully added role with name '{}' to user with username '{}'", roleName, username);
    }

    public User findByAuthentication(Authentication authentication) {
        if (authentication == null) {
            return null;
        }
        return findUser(authentication.getName());
    }

    public User findUser(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            log.error("Failed to find user with username '{}'", username);
        } else {
            log.info("Successfully found user with username '{}'", username);
        }
        return user;
    }

    public User findUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.error("Failed to find user with id '{}'", id);
        } else {
            log.info("Successfully found user with id '{}'", id);
        }
        return user;
    }

    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            log.error("User with id '{}' not found", id);
            throw new UsernameNotFoundException(String.format("User with id '%d' not found", id));
        }
        log.info("User with id '{}' found successfully", id);
        return user.get();
    }

    public List<User> getUsers() {
        log.info("Finding all users");
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUser(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with username '%s' was not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.getRoles());
    }
}
