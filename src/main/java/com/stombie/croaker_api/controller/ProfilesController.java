package com.stombie.croaker_api.controller;

import com.stombie.croaker_api.models.ProfileGetDto;
import com.stombie.croaker_api.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/profiles")
public class ProfilesController {
    private final ProfileService profileService;

    @Autowired
    public ProfilesController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ProfileGetDto> index(@PathVariable(required = true) Long userId) {
        Optional<ProfileGetDto> profileGetDto = profileService.findProfileDtoByUserId(userId);
        if (profileGetDto.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(profileGetDto.get());
    }
}
