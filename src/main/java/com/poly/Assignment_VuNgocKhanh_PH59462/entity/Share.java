package com.poly.Assignment_VuNgocKhanh_PH59462.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Shares")
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Emails", nullable = false)
    private String emails;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ShareDate")
    private Date shareDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VideoId")
    private Video video;

    public Share() {
    }

    public Share(String emails, Date shareDate, User user, Video video) {
        this.emails = emails;
        this.shareDate = shareDate;
        this.user = user;
        this.video = video;
    }

    public Long getId() {
        return id;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public Date getShareDate() {
        return shareDate;
    }

    public void setShareDate(Date shareDate) {
        this.shareDate = shareDate;
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
        return "Share{" +
                "id=" + id +
                ", emails='" + emails + '\'' +
                ", shareDate=" + shareDate +
                '}';
    }
}