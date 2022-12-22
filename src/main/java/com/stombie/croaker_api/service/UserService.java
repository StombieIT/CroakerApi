package com.stombie.croaker_api.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
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

    public User saveUser(User user) {
        log.info("Successfully saved user with username '{}'", user.getUsername());
        return userRepository.save(user);
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

    public User getUser(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            log.error("Failed to find user with username '{}'", username);
        } else {
            log.info("Successfully found user with username '{}'", username);
        }
        return user;
    }

    public List<User> getUsers() {
        log.info("Finding all users");
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with username '%s' was not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.getRoles());
    }
}
