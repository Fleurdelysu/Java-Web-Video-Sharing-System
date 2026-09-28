package com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl;

import com.poly.Assignment_VuNgocKhanh_PH59462.dao.GenericDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.ShareDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Share;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.User;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Video;
import com.poly.Assignment_VuNgocKhanh_PH59462.utils.HibernateUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;


public class ShareDAOImpl implements ShareDAO {


    private final GenericDAO<Share> genericDAO =
            new GenericDAO<>(Share.class);



    @Override
    public Share findById(Long id) {

        return genericDAO.findById(id);

    }



    @Override
    public List<Share> findByVideo(Video video) {


        EntityManager em =
                HibernateUtil.getEntityManager();


        try {


            String jpql =
                    "SELECT s FROM Share s "
                            + "WHERE s.video.id = :videoId";


            TypedQuery<Share> query =
                    em.createQuery(
                            jpql,
                            Share.class
                    );


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
    public List<Share> findByUser(User user) {


        EntityManager em =
                HibernateUtil.getEntityManager();


        try {


            String jpql =
                    "SELECT s FROM Share s "
                            + "WHERE s.user.id = :userId";


            TypedQuery<Share> query =
                    em.createQuery(
                            jpql,
                            Share.class
                    );


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
    public Long countByVideo(Video video) {


        EntityManager em =
                HibernateUtil.getEntityManager();


        try {


            String jpql =
                    "SELECT COUNT(s) FROM Share s "
                            + "WHERE s.video.id = :videoId";


            TypedQuery<Long> query =
                    em.createQuery(
                            jpql,
                            Long.class
                    );


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
    public List<Share> shareHistory() {


        EntityManager em =
                HibernateUtil.getEntityManager();


        try {


            String jpql =
                    "SELECT s FROM Share s "
                            + "ORDER BY s.shareDate DESC";


            TypedQuery<Share> query =
                    em.createQuery(
                            jpql,
                            Share.class
                    );


            return query.getResultList();



        } finally {

            em.close();

        }

    }





    @Override
    public List<Share> findAll() {

        return genericDAO.findAll();

    }





    @Override
    public void create(Share share) {

        genericDAO.create(share);

    }





    @Override
    public void update(Share share) {

        genericDAO.update(share);

    }





    @Override
    public void delete(Long id) {

        genericDAO.delete(id);

    }

}