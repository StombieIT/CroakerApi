package com.stombie.croaker_api.service;

import com.stombie.croaker_api.entity.Croak;
import com.stombie.croaker_api.entity.User;
import com.stombie.croaker_api.exception.UserNotFoundException;
import com.stombie.croaker_api.models.CroakGetDto;
import com.stombie.croaker_api.models.Reaction;
import com.stombie.croaker_api.repo.CroakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CroakService {
    private final CroakRepository croakRepository;
    private final LikeService likeService;
    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public CroakService(CroakRepository croakRepository,
                        LikeService likeService,
                        CommentService commentService,
                        UserService userService) {
        this.croakRepository = croakRepository;
        this.likeService = likeService;
        this.commentService = commentService;
        this.userService = userService;
    }

    public Reaction getReaction(Croak originalCroak, User user) {
        Long originalCroakId = originalCroak == null ? null : originalCroak.getId();
        Long userId = user == null ? null : user.getId();
        return Reaction.of(
            croakRepository.getCountByOriginalCroakId(originalCroakId),
            croakRepository.isActiveByOriginalCroakIdAndUserId(originalCroakId, userId)
        );
    }

    public List<CroakGetDto> getSortedByCreationDateDescDtoList(Long userId, User checker) throws UserNotFoundException {
        userService.getUserById(userId);
        return croakRepository.findAllByAuthorIdOrderingByCreationDateDesc(userId)
                .stream()
                .map(croak -> new CroakGetDto(
                    croak,
                    likeService.getReaction(croak, checker),
                    commentService.getReaction(croak, checker),
                    getReaction(croak, checker)
                ))
                .collect(Collectors.toList());
    }
}
