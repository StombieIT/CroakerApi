package com.stombie.croaker_api.service;

import com.stombie.croaker_api.entity.User;
import com.stombie.croaker_api.models.CroakGetDto;
import com.stombie.croaker_api.repo.CroakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CroakService {
    private final CroakRepository croakRepository;
    private final LikeService likeService;

    @Autowired
    public CroakService(CroakRepository croakRepository, LikeService likeService) {
        this.croakRepository = croakRepository;
        this.likeService = likeService;
    }

    public List<CroakGetDto> getSortedByCreationDateDescDtoList(User user) {
        return croakRepository.findAll(Sort.by(Sort.Order.desc("creationDate")))
                .stream()
                .map(croak -> new CroakGetDto(croak, likeService.getReaction(croak, user)))
                .collect(Collectors.toList());
    }
}
