package com.stombie.croaker_api.models;

import com.stombie.croaker_api.entity.Croak;
import com.stombie.croaker_api.util.MappingUtils;

import java.util.Date;
import java.util.stream.Collectors;

public class CroakGetDto {
    private final Croak croak;
    private final Reaction likes;

    public CroakGetDto(Croak croak, Reaction likes) {
        this.croak = croak;
        this.likes = likes;
    }

    public Long getId() {
        return croak.getId();
    }

    public String getText() {
        return croak.getText();
    }

    public Date getCreationDate() {
        return croak.getCreationDate();
    }

    public UserGetDto getAuthor() {
        return new UserGetDto(croak.getAuthor());
    }

    public Iterable<String> getImagesLinks() {
        return croak.getImages().stream().map(MappingUtils::getLink).collect(Collectors.toSet());
    }

    public Reaction getLikes() {
        return likes;
    }
}
