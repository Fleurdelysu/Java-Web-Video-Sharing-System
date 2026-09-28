package com.poly.Assignment_VuNgocKhanh_PH59462.dao;

import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Share;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.User;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Video;

import java.util.List;

public interface ShareDAO {

    Share findById(Long id);

    List<Share> findByVideo(Video video);

    List<Share> findByUser(User user);

    Long countByVideo(Video video);

    List<Share> shareHistory();

    List<Share> findAll();

    void create(Share share);

    void update(Share share);

    void delete(Long id);

}