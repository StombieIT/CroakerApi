package com.stombie.croaker_api.controller;

import com.stombie.croaker_api.entity.User;
import com.stombie.croaker_api.exception.UserAlreadyExistsException;
import com.stombie.croaker_api.models.Message;
import com.stombie.croaker_api.models.UserGetDto;
import com.stombie.croaker_api.models.UserPostDto;
import com.stombie.croaker_api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<UserGetDto>> index() {
        return ResponseEntity.ok().body(userService.getUsers()
                .stream()
                .map(UserGetDto::new)
                .collect(Collectors.toList()));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserPostDto userPostDto) {
        try {
            User user = userService.saveUser(userPostDto.toUser());
            return ResponseEntity.ok().body(UserGetDto.from(user));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(Message.of(e.getMessage()));
        }
    }
}
