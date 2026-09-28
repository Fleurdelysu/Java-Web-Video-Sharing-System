package com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl;

import com.poly.Assignment_VuNgocKhanh_PH59462.dao.GenericDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.VideoDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Video;
import com.poly.Assignment_VuNgocKhanh_PH59462.utils.HibernateUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;


public class VideoDAOImpl implements VideoDAO {


    private final GenericDAO<Video> genericDAO =
            new GenericDAO<>(Video.class);



    @Override
    public Video findById(String id) {

        return genericDAO.findById(id);

    }



    @Override
    public List<Video> findAll() {

        return genericDAO.findAll();

    }



    @Override
    public List<Video> findActiveVideos() {


        EntityManager em =
                HibernateUtil.getEntityManager();


        try {


            String jpql =
                    "SELECT v FROM Video v "
                            + "WHERE v.active = true";


            TypedQuery<Video> query =
                    em.createQuery(jpql, Video.class);



            return query.getResultList();



        } finally {

            em.close();

        }

    }





    @Override
    public long countActiveVideos() {

        EntityManager em =
                HibernateUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT COUNT(v) FROM Video v "
                            + "WHERE v.active = true";

            return em.createQuery(jpql, Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }



    @Override
    public List<Video> findActiveVideosPaged(
            int page,
            int pageSize) {

        EntityManager em =
                HibernateUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT v FROM Video v "
                            + "WHERE v.active = true "
                            + "ORDER BY v.views DESC";

            TypedQuery<Video> query =
                    em.createQuery(jpql, Video.class);

            query.setFirstResult(
                    (page - 1) * pageSize
            );

            query.setMaxResults(pageSize);

            return query.getResultList();

        } finally {

            em.close();

        }

    }



    @Override
    public List<Video> findByTitle(String keyword) {


        EntityManager em =
                HibernateUtil.getEntityManager();


        try {


            String jpql =
                    "SELECT v FROM Video v "
                            + "WHERE v.title LIKE :keyword";


            TypedQuery<Video> query =
                    em.createQuery(jpql, Video.class);



            query.setParameter(
                    "keyword",
                    "%" + keyword + "%"
            );



            return query.getResultList();



        } finally {

            em.close();

        }

    }





    @Override
    public List<Video> findRelatedVideos(String videoId) {


        EntityManager em =
                HibernateUtil.getEntityManager();


        try {


            String jpql =
                    "SELECT v FROM Video v "
                            + "WHERE v.id <> :id "
                            + "AND v.active = true";


            TypedQuery<Video> query =
                    em.createQuery(jpql, Video.class);



            query.setParameter(
                    "id",
                    videoId
            );


            query.setMaxResults(5);



            return query.getResultList();



        } finally {

            em.close();

        }

    }





    @Override
    public List<Video> findTopFavoriteVideos() {


        EntityManager em =
                HibernateUtil.getEntityManager();


        try {


            String jpql =
                    "SELECT v FROM Video v "
                            + "LEFT JOIN v.favorites f "
                            + "GROUP BY v "
                            + "ORDER BY COUNT(f) DESC";



            TypedQuery<Video> query =
                    em.createQuery(jpql, Video.class);



            query.setMaxResults(10);



            return query.getResultList();



        } finally {

            em.close();

        }

    }





    @Override
    public void create(Video video) {


        genericDAO.create(video);


    }




    @Override
    public void update(Video video) {


        genericDAO.update(video);


    }




    @Override
    public void delete(String id) {


        genericDAO.delete(id);


    }
    @Override
    public long countVideos() {

        EntityManager em =
                HibernateUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT COUNT(v) FROM Video v";

            return em.createQuery(
                    jpql,
                    Long.class
            ).getSingleResult();

        } finally {

            em.close();
        }
    }
    @Override
    public List<Video> findVideosPaged(
            int page,
            int pageSize) {

        EntityManager em =
                HibernateUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT v FROM Video v "
                            + "ORDER BY v.id";

            TypedQuery<Video> query =
                    em.createQuery(
                            jpql,
                            Video.class
                    );

            query.setFirstResult(
                    (page - 1) * pageSize
            );

            query.setMaxResults(pageSize);

            return query.getResultList();

        } finally {

            em.close();
        }
    }
}