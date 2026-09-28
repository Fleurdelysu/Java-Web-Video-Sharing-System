package com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl;

import com.poly.Assignment_VuNgocKhanh_PH59462.dao.GenericDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.UserDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.User;
import com.poly.Assignment_VuNgocKhanh_PH59462.utils.HibernateUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;


public class UserDAOImpl implements UserDAO {


    private final GenericDAO<User> genericDAO =
            new GenericDAO<>(User.class);



    @Override
    public User findById(String id) {

        return genericDAO.findById(id);

    }



    @Override
    public User findByEmail(String email) {


        EntityManager em =
                HibernateUtil.getEntityManager();


        try {


            String jpql =
                    "SELECT u FROM User u "
                            + "WHERE u.email = :email";


            TypedQuery<User> query =
                    em.createQuery(jpql, User.class);


            query.setParameter("email", email);


            List<User> result =
                    query.getResultList();


            return result.isEmpty()
                    ? null
                    : result.get(0);



        } finally {

            em.close();

        }
    }



    @Override
    public User findByEmailAndPassword(
            String email,
            String password) {


        EntityManager em =
                HibernateUtil.getEntityManager();


        try {


            String jpql =
                    "SELECT u FROM User u "
                            + "WHERE u.email = :email "
                            + "AND u.password = :password";


            TypedQuery<User> query =
                    em.createQuery(jpql, User.class);


            query.setParameter("email", email);

            query.setParameter("password", password);



            List<User> result =
                    query.getResultList();



            return result.isEmpty()
                    ? null
                    : result.get(0);



        } finally {

            em.close();

        }

    }




    @Override
    public List<User> findAllAdmins() {


        EntityManager em =
                HibernateUtil.getEntityManager();


        try {


            String jpql =
                    "SELECT u FROM User u "
                            + "WHERE u.admin = true";


            TypedQuery<User> query =
                    em.createQuery(jpql, User.class);



            return query.getResultList();



        } finally {

            em.close();

        }

    }





    @Override
    public List<User> findAll() {


        return genericDAO.findAll();


    }




    @Override
    public void create(User user) {


        genericDAO.create(user);


    }




    @Override
    public void update(User user) {


        genericDAO.update(user);


    }




    @Override
    public void delete(String id) {


        genericDAO.delete(id);


    }

    @Override
    public long countUsers() {

        EntityManager em =
                HibernateUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT COUNT(u) FROM User u";

            return em.createQuery(
                    jpql,
                    Long.class
            ).getSingleResult();

        } finally {

            em.close();
        }
    }

    @Override
    public List<User> findUsersPaged(
            int page,
            int pageSize) {

        EntityManager em =
                HibernateUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT u FROM User u "
                            + "ORDER BY u.id";

            TypedQuery<User> query =
                    em.createQuery(
                            jpql,
                            User.class
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