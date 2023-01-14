package com.stombie.croaker_api.service;

import com.stombie.croaker_api.entity.Croak;
import com.stombie.croaker_api.entity.User;
import com.stombie.croaker_api.models.Reaction;
import com.stombie.croaker_api.repo.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private final LikeRepository likeRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public Reaction getReaction(Croak croak, User user) {
        Long croakId = croak == null ? null : croak.getId();
        Long userId = user == null ? null : user.getId();
        return Reaction.of(likeRepository.getCountByCroakId(croakId), likeRepository.isActiveByCroakIdAndUserId(croakId, userId));
    }
}
