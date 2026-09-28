package com.poly.Assignment_VuNgocKhanh_PH59462.test;

import com.poly.Assignment_VuNgocKhanh_PH59462.utils.JpaUtil;
import jakarta.persistence.EntityManager;

public class TestConnection {

    public static void main(String[] args) {
        try {
            EntityManager em = JpaUtil.getEntityManager();

            System.out.println("================================");
            System.out.println("Hibernate kết nối thành công!");
            System.out.println("================================");

            em.close();
            JpaUtil.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}