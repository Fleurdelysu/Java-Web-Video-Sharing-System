package com.poly.Assignment_VuNgocKhanh_PH59462.dao;

import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Video;

import java.util.List;

public interface VideoDAO {

    Video findById(String id);

    List<Video> findAll();

    List<Video> findActiveVideos();

    long countActiveVideos();

    List<Video> findActiveVideosPaged(int page, int pageSize);

    List<Video> findByTitle(String keyword);

    List<Video> findRelatedVideos(String videoId);

    List<Video> findTopFavoriteVideos();

    void create(Video video);

    void update(Video video);

    void delete(String id);
    long countVideos();

    List<Video> findVideosPaged(int page, int pageSize);
}