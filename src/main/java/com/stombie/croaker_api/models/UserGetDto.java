package com.stombie.croaker_api.models;

import com.stombie.croaker_api.entity.Image;
import com.stombie.croaker_api.entity.User;
import com.stombie.croaker_api.util.MappingUtils;

public class UserGetDto {
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
        Image avatar = user.getAvatar();
        if (avatar == null) {
            return null;
        }
        return MappingUtils.getLink(avatar.getFilename());
    }
}
