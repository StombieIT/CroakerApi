package com.stombie.croaker_api.service;

import com.stombie.croaker_api.entity.Profile;
import com.stombie.croaker_api.exception.UserNotFoundException;
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

    public ProfileGetDto getProfileDtoByUserId(Long userId) throws UserNotFoundException {
        Optional<Profile> profile = profileRepository.findById(userId);
        if (profile.isEmpty()) {
            log.error("Profile of user with id '{}' not found", userId);
            throw new UserNotFoundException(String.format("Profile of user with id '%s' not found", userId));
        }
        log.info("Profile of user with id '{}' found successfully", userId);
        return new ProfileGetDto(
            profile.get(),
            croakRepository.getCountByAuthorId(profile.get().getUser().getId()),
            userRepository.getFollowersCountByUserId(profile.get().getUser().getId()),
            userRepository.getFollowingCountByUserId(profile.get().getUser().getId())
        );
    }
}
