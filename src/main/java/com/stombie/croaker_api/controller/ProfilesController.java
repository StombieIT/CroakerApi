package com.stombie.croaker_api.controller;

import com.stombie.croaker_api.exception.UserNotFoundException;
import com.stombie.croaker_api.models.Message;
import com.stombie.croaker_api.models.ProfileGetDto;
import com.stombie.croaker_api.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profiles")
public class ProfilesController {
    private final ProfileService profileService;

    @Autowired
    public ProfilesController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> index(@PathVariable(required = true) Long userId) {
        try {
            ProfileGetDto profileGetDto = profileService.getProfileDtoByUserId(userId);
            return ResponseEntity.ok().body(profileGetDto);
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(Message.of(e.getMessage()));
        }
    }
}
