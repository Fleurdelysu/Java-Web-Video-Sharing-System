package com.poly.Assignment_VuNgocKhanh_PH59462.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Videos")
public class Video {

    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Poster")
    private String poster;

    @Column(name = "Views", nullable = false)
    private Integer views;

    @Column(name = "Description")
    private String description;

    @Column(name = "Active", nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "video",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Favorite> favorites = new HashSet<>();

    @OneToMany(mappedBy = "video",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Share> shares = new HashSet<>();

    public Video() {
    }

    public Video(String id, String title, String poster,
                 Integer views, String description, Boolean active) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.views = views;
        this.description = description;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }

    public Set<Share> getShares() {
        return shares;
    }

    public void setShares(Set<Share> shares) {
        this.shares = shares;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", views=" + views +
                ", active=" + active +
                '}';
    }
}