package com.stombie.croaker_api.controller;

import com.stombie.croaker_api.models.UserGetDto;
import com.stombie.croaker_api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
