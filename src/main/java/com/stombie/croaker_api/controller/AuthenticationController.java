package com.stombie.croaker_api.controller;

import com.stombie.croaker_api.Constants;
import com.stombie.croaker_api.JsonWebTokens;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class AuthenticationController {
    @GetMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(Authentication authentication) {
        return ResponseEntity.ok().body(Collections.singletonMap(
                "accessToken", JsonWebTokens.createToken(authentication, Constants.ALGORITHM, Constants.ACCESS_TOKEN_LIFETIME_IN_MILLIS)
        ));
    }
}
