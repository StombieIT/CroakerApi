package com.stombie.croaker_api.service;

import com.stombie.croaker_api.entity.Croak;
import com.stombie.croaker_api.entity.User;
import com.stombie.croaker_api.models.CroakGetDto;
import com.stombie.croaker_api.models.Reaction;
import com.stombie.croaker_api.repo.CroakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CroakService {
    private final CroakRepository croakRepository;
    private final LikeService likeService;
    private final CommentService commentService;

    public Reaction getReaction(Croak originalCroak, User user) {
        return Reaction.of(
            croakRepository.getCountByOriginalCroakId(originalCroak.getId()),
            croakRepository.isActiveByOriginalCroakIdAndUserId(originalCroak.getId(), user.getId())
        );
    }

    @Autowired
    public CroakService(CroakRepository croakRepository,
                        LikeService likeService,
                        CommentService commentService) {
        this.croakRepository = croakRepository;
        this.likeService = likeService;
        this.commentService = commentService;
    }

    public List<CroakGetDto> getSortedByCreationDateDescDtoList(User user) {
        return croakRepository.findAll(Sort.by(Sort.Order.desc("creationDate")))
                .stream()
                .map(croak -> new CroakGetDto(
                        croak,
                        likeService.getReaction(croak, user),
                        commentService.getReaction(croak, user),
                        getReaction(croak, user)
                ))
                .collect(Collectors.toList());
    }
}
