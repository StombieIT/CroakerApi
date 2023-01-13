package com.stombie.croaker_api.models;

public class Reaction {

    public static Reaction of(Long count, Boolean isActive) {
        return new Reaction(count, isActive);
    }

    private final Long count;
    private final Boolean isActive;

    public Reaction(Long count, Boolean isActive) {
        this.count = count;
        this.isActive = isActive;
    }

    public Long getCount() {
        return count;
    }

    public Boolean getIsActive() {
        return isActive;
    }
}
