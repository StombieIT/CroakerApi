package com.stombie.croaker_api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @Column(name = "user_id")
    private Long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
}
