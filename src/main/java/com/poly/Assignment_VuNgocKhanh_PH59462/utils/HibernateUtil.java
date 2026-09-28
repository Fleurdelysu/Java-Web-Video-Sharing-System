package com.poly.Assignment_VuNgocKhanh_PH59462.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {

    // Tạo duy nhất một EntityManagerFactory cho toàn bộ project
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("ASM_PolyOE");

    // Lấy EntityManager
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Đóng EntityManagerFactory khi ứng dụng kết thúc
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}