package com.poly.Assignment_VuNgocKhanh_PH59462.dao;

import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Favorite;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.User;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Video;

import java.util.List;

public interface FavoriteDAO {

    Favorite findById(Long id);

    Favorite findByUserAndVideo(User user, Video video);

    List<Favorite> findByUser(User user);

    List<Favorite> findByVideo(Video video);

    Long countByVideo(Video video);

    List<User> favoriteUsersByVideo(Video video);

    List<Favorite> findAll();

    void create(Favorite favorite);

    void update(Favorite favorite);

    void delete(Long id);

}