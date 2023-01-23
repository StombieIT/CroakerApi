package com.stombie.croaker_api.controller;

import com.stombie.croaker_api.entity.User;
import com.stombie.croaker_api.exception.UserNotFoundException;
import com.stombie.croaker_api.models.CroakGetDto;
import com.stombie.croaker_api.models.Message;
import com.stombie.croaker_api.models.Paginator;
import com.stombie.croaker_api.service.CroakService;
import com.stombie.croaker_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/croaks")
@RestController
public class CroaksController {
    private final CroakService croakService;
    private final UserService userService;

    @Autowired
    public CroaksController(CroakService croakService, UserService userService) {
        this.croakService = croakService;
        this.userService = userService;
    }

    @GetMapping("/by-user-id/{userId}")
    public ResponseEntity<?> byUserId(@PathVariable Long userId,
                                      @RequestParam(required = false, defaultValue = "1") int page,
                                      @RequestParam(required = false, defaultValue = "10") int limit,
                                      Authentication authentication) {
        if (page <= 0 || limit <= 0) {
            return ResponseEntity.badRequest().build();
        }
        User user = userService.findByAuthentication(authentication);
        try {
            List<CroakGetDto> croaks = croakService.getSortedByCreationDateDescDtoList(userId, user);
            return ResponseEntity.ok().body(Paginator.of(croaks, page, limit));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(Message.of(e.getMessage()));
        }
    }
}
