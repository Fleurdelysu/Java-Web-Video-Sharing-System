package com.poly.Assignment_VuNgocKhanh_PH59462.dao;

import com.poly.Assignment_VuNgocKhanh_PH59462.entity.User;

import java.util.List;

public interface UserDAO {

    User findById(String id);

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    List<User> findAll();

    List<User> findAllAdmins();

    long countUsers();

    List<User> findUsersPaged(int page, int pageSize);

    void create(User user);

    void update(User user);

    void delete(String id);
}