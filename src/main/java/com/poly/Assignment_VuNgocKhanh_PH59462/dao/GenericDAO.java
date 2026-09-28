package com.poly.Assignment_VuNgocKhanh_PH59462.dao;

import com.poly.Assignment_VuNgocKhanh_PH59462.utils.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class GenericDAO<T> {

    private final Class<T> clazz;

    public GenericDAO(Class<T> clazz) {
        this.clazz = clazz;
    }


    // CREATE
    public void create(T entity) {

        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {

            transaction.begin();

            em.persist(entity);

            transaction.commit();

        } catch (Exception e) {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            throw e;

        } finally {

            em.close();

        }
    }


    // UPDATE
    public void update(T entity) {

        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {

            transaction.begin();

            em.merge(entity);

            transaction.commit();

        } catch (Exception e) {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            throw e;

        } finally {

            em.close();

        }
    }


    // DELETE
    public void delete(Object id) {

        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {

            transaction.begin();

            T entity = em.find(clazz, id);

            if (entity != null) {
                em.remove(entity);
            }

            transaction.commit();

        } catch (Exception e) {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            throw e;

        } finally {

            em.close();

        }
    }


    // FIND BY ID
    public T findById(Object id) {

        EntityManager em = HibernateUtil.getEntityManager();

        try {

            return em.find(clazz, id);

        } finally {

            em.close();

        }
    }


    // FIND ALL
    public List<T> findAll() {

        EntityManager em = HibernateUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT e FROM "
                            + clazz.getSimpleName()
                            + " e";


            TypedQuery<T> query =
                    em.createQuery(jpql, clazz);


            return query.getResultList();


        } finally {

            em.close();

        }
    }
}