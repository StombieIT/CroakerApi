package com.stombie.croaker_api.service;

import com.stombie.croaker_api.entity.Profile;
import com.stombie.croaker_api.models.ProfileGetDto;
import com.stombie.croaker_api.repo.CroakRepository;
import com.stombie.croaker_api.repo.ProfileRepository;
import com.stombie.croaker_api.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ProfileService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final CroakRepository croakRepository;

    @Autowired
    public ProfileService(UserRepository userRepository,
                          ProfileRepository profileRepository,
                          CroakRepository croakRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.croakRepository = croakRepository;
    }

    public Optional<ProfileGetDto> findProfileDtoByUserId(Long userId) {
        Optional<Profile> profile = profileRepository.findById(userId);
        if (profile.isPresent()) {
            log.info("Profile '{}' successfully found", userId);
        } else {
            log.error("Profile '{}' not found", userId);
        }
        return profile.map(p -> new ProfileGetDto(
            p,
            croakRepository.getCountByAuthorId(p.getUser().getId()),
            userRepository.getFollowersCountByUserId(p.getUser().getId()),
            userRepository.getFollowingCountByUserId(p.getUser().getId())
        ));
    }
}
