package com.stombie.croaker_api.models;

import com.stombie.croaker_api.entity.User;
import com.stombie.croaker_api.util.MappingUtils;

import java.util.Date;

public class UserGetDto {
    public static UserGetDto from(User user) {
        return new UserGetDto(user);
    }

    private final User user;

    public UserGetDto(User user) {
        this.user = user;
    }

    public Long getId() {
        return user.getId();
    }

    public String getName() {
        return user.getName();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getAvatarLink() {
        return MappingUtils.getLink(user.getAvatar());
    }

    public Date getRegistrationDate() {
        return user.getRegistrationDate();
    }
}
