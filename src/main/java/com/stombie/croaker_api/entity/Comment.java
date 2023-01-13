package com.stombie.croaker_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "comments")
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false, length = 1024)
    private String text;
    @Column(nullable = false)
    private Date creationDate = new Date();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "croak_id")
    private Croak croak;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "comments_images",
            joinColumns = @JoinColumn(name = "comments_id"),
            inverseJoinColumns = @JoinColumn(name = "images_id")
    )
    private Set<Image> images = new HashSet<>();

    public Comment() {
    }

    public Comment(User author, Croak croak, String text) {
        this.author = author;
        this.croak = croak;
        this.text = text;
    }

    public Comment(User author, Croak croak, String text, Set<Image> images) {
        this(author, croak, text);
        this.images = images;
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

    public void setText(String text) {
        this.text = text;
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

    @JsonIgnore
    public Croak getCroak() {
        return croak;
    }
}
