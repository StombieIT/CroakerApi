package com.stombie.croaker_api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private User user;
    @Column(length = 512)
    private String description;
    @Column(nullable = false, length = 128)
    private String city;

    @Column(nullable = false, length = 128)
    private String country;
    @ManyToOne(fetch = FetchType.EAGER)
    private Image backgroundImage;
    public Profile() {
    }

    public Profile(User user) {
        this.user = user;
    }

    public Profile(User user, String description) {
        this(user);
        this.description = description;
    }

    public Profile(User user, String description, Image backgroundImage) {
        this(user, description);
        this.backgroundImage = backgroundImage;
    }
}
