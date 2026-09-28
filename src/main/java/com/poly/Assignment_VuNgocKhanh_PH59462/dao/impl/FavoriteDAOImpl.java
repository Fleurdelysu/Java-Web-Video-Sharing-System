package com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl;

import com.poly.Assignment_VuNgocKhanh_PH59462.dao.FavoriteDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.GenericDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Favorite;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.User;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Video;
import com.poly.Assignment_VuNgocKhanh_PH59462.utils.HibernateUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;


public class FavoriteDAOImpl implements FavoriteDAO {


    private final GenericDAO<Favorite> genericDAO =
            new GenericDAO<>(Favorite.class);



    @Override
    public Favorite findById(Long id) {

        return genericDAO.findById(id);

    }



    @Override
    public Favorite findByUserAndVideo(
            User user,
            Video video) {


        EntityManager em =
                HibernateUtil.getEntityManager();


        try {


            String jpql =
                    "SELECT f FROM Favorite f "
                            + "WHERE f.user.id = :userId "
                            + "AND f.video.id = :videoId";


            TypedQuery<Favorite> query =
                    em.createQuery(jpql, Favorite.class);



            query.setParameter(
                    "userId",
                    user.getId()
            );


            query.setParameter(
                    "videoId",
                    video.getId()
            );



            List<Favorite> result =
                    query.getResultList();



            return result.isEmpty()
                    ? null
                    : result.get(0);



        } finally {

            em.close();

        }

    }





    @Override
    public List<Favorite> findByUser(User user) {


        EntityManager em =
                HibernateUtil.getEntityManager();


        try {


            String jpql =
                    "SELECT f FROM Favorite f "
                            + "JOIN FETCH f.video "
                            + "WHERE f.user.id = :userId "
                            + "ORDER BY f.likeDate DESC";


            TypedQuery<Favorite> query =
                    em.createQuery(jpql, Favorite.class);



            query.setParameter(
                    "userId",
                    user.getId()
            );



            return query.getResultList();



        } finally {

            em.close();

        }

    }





    @Override
    public List<Favorite> findByVideo(Video video) {


        EntityManager em =
                HibernateUtil.getEntityManager();


        try {


            String jpql =
                    "SELECT f FROM Favorite f "
                            + "WHERE f.video.id = :videoId";


            TypedQuery<Favorite> query =
                    em.createQuery(jpql, Favorite.class);



            query.setParameter(
                    "videoId",
                    video.getId()
            );



            return query.getResultList();



        } finally {

            em.close();

        }

    }





    @Override
    public Long countByVideo(Video video) {


        EntityManager em =
                HibernateUtil.getEntityManager();


        try {


            String jpql =
                    "SELECT COUNT(f) FROM Favorite f "
                            + "WHERE f.video.id = :videoId";



            TypedQuery<Long> query =
                    em.createQuery(jpql, Long.class);



            query.setParameter(
                    "videoId",
                    video.getId()
            );



            return query.getSingleResult();



        } finally {

            em.close();

        }

    }





    @Override
    public List<User> favoriteUsersByVideo(Video video) {


        EntityManager em =
                HibernateUtil.getEntityManager();


        try {


            String jpql =
                    "SELECT u FROM User u "
                            + "JOIN u.favorites f "
                            + "WHERE f.video.id = :videoId";


            TypedQuery<User> query =
                    em.createQuery(jpql, User.class);



            query.setParameter(
                    "videoId",
                    video.getId()
            );



            return query.getResultList();



        } finally {

            em.close();

        }

    }





    @Override
    public List<Favorite> findAll() {

        return genericDAO.findAll();

    }




    @Override
    public void create(Favorite favorite) {

        genericDAO.create(favorite);

    }




    @Override
    public void update(Favorite favorite) {

        genericDAO.update(favorite);

    }




    @Override
    public void delete(Long id) {

        genericDAO.delete(id);

    }

}