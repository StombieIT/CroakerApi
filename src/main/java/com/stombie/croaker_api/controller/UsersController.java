package com.stombie.croaker_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stombie.croaker_api.entity.User;
import com.stombie.croaker_api.service.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<User>> index() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/my")
    public ResponseEntity<User> my(Authentication authentication) {
        User user = userService.getUser(authentication.getName());
        return ResponseEntity.ok().body(user);
    }
}
