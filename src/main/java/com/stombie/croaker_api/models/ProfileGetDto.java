package com.stombie.croaker_api.models;

import com.stombie.croaker_api.entity.Profile;
import com.stombie.croaker_api.util.MappingUtils;

public class ProfileGetDto {
    private final Profile profile;
    private final Long followersCount;
    private final Long croaksCount;
    private final Long followingCount;

    public ProfileGetDto(Profile profile, Long croaksCount, Long followersCount, Long followingCount) {
        this.profile = profile;
        this.followersCount = followersCount;
        this.croaksCount = croaksCount;
        this.followingCount = followingCount;
    }

    public UserGetDto getUser() {
        return new UserGetDto(profile.getUser());
    }

    public String getDescription() {
        return profile.getDescription();
    }

    public String getCity() {
        return profile.getCity();
    }

    public String getCountry() {
        return profile.getCountry();
    }

    public Long getFollowersCount() {
        return followersCount;
    }

    public Long getCroaksCount() {
        return croaksCount;
    }

    public Long getFollowingCount() {
        return followingCount;
    }

    public String backgroundImageLink() {
        return MappingUtils.getLink(profile.getBackgroundImage().getFilename());
    }
}
