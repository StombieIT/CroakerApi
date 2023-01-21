package com.stombie.croaker_api.service;

import com.stombie.croaker_api.entity.Croak;
import com.stombie.croaker_api.entity.User;
import com.stombie.croaker_api.models.Reaction;
import com.stombie.croaker_api.repo.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Reaction getReaction(Croak croak, User user) {
        Long croakId = croak == null ? null : croak.getId();
        Long userId = user == null ? null : user.getId();
        return Reaction.of(commentRepository.getCountByCroakId(croakId), commentRepository.isActiveByCroakIdAndUserId(croakId, userId));
    }
}
