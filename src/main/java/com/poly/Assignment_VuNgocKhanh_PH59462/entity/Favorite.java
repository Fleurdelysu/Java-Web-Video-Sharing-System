package com.poly.Assignment_VuNgocKhanh_PH59462.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LikeDate")
    private Date likeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VideoId")
    private Video video;

    public Favorite() {
    }

    public Favorite(Date likeDate, User user, Video video) {
        this.likeDate = likeDate;
        this.user = user;
        this.video = video;
    }

    public Long getId() {
        return id;
    }

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", likeDate=" + likeDate +
                '}';
    }
}