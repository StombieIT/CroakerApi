package com.stombie.croaker_api.controller;

import com.stombie.croaker_api.entity.User;
import com.stombie.croaker_api.models.Reaction;
import com.stombie.croaker_api.repo.LikeRepository;
import com.stombie.croaker_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
public class LikesController {
    private final LikeRepository likeRepository;
    private final UserService userService;

    @Autowired
    public LikesController(LikeRepository likeRepository, UserService userService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
    }

    @GetMapping("/get-reaction")
    public ResponseEntity<Reaction> getReaction(@RequestParam Long croakId, Authentication authentication) {
        Long likesCount = likeRepository.getCountByCroakId(croakId);
        if (authentication == null) {
            return ResponseEntity.ok().body(Reaction.of(likesCount,false));
        }
        User user = userService.getUser(authentication.getName());
        Boolean isActive = likeRepository.isActiveByCroakIdAndUserId(croakId, user.getId());
        return ResponseEntity.ok().body(Reaction.of(likesCount, isActive));
    }
}
