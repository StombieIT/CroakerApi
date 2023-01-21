package com.stombie.croaker_api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "croaks")
public class Croak {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false, length = 4096)
    private String text;
    @Column(nullable = false)
    private Date creationDate = new Date();
    @ManyToOne(fetch = FetchType.EAGER)
    private User author;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "croak")
    private Set<Like> likes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "croak")
    private Set<Comment> comments = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "croaks_images",
            joinColumns = @JoinColumn(name = "croak_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private Set<Image> images = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "originalCroak")
    private Set<Croak> replies = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private Croak originalCroak;

    public Croak() {
    }

    public Croak(User author, String text) {
        this.author = author;
        this.text = text;
    }

    public Croak(User author, String text, Croak originalCroak) {
        this(author, text);
        this.originalCroak = originalCroak;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<Croak> getReplies() {
        return replies;
    }

    public void setReplies(Set<Croak> replies) {
        this.replies = replies;
    }

    public Croak getOriginalCroak() {
        return originalCroak;
    }

    public void setOriginalCroak(Croak originalCroak) {
        this.originalCroak = originalCroak;
    }
}
