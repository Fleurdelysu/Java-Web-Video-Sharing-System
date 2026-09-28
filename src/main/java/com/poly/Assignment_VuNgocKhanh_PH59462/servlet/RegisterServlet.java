package com.poly.Assignment_VuNgocKhanh_PH59462.servlet;


import com.poly.Assignment_VuNgocKhanh_PH59462.dao.UserDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl.UserDAOImpl;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.User;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;



@WebServlet("/register")
public class RegisterServlet extends HttpServlet {



    private final UserDAO userDAO =
            new UserDAOImpl();




    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)

            throws ServletException, IOException {


        request.getRequestDispatcher(
                "/views/register.jsp"
        ).forward(
                request,
                response
        );

    }







    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)

            throws ServletException, IOException {



        String id =
                request.getParameter("id");


        String password =
                request.getParameter("password");


        String confirmPassword =
                request.getParameter("confirmPassword");


        String fullname =
                request.getParameter("fullname");


        String email =
                request.getParameter("email");





        // kiểm tra rỗng

        if(id == null || id.isBlank()
                || password == null || password.isBlank()
                || fullname == null || fullname.isBlank()
                || email == null || email.isBlank()){


            request.setAttribute(
                    "message",
                    "Vui lòng nhập đầy đủ thông tin"
            );


            request.getRequestDispatcher(
                    "/views/register.jsp"
            ).forward(
                    request,
                    response
            );


            return;

        }





        // kiểm tra password

        if(!password.equals(confirmPassword)){


            request.setAttribute(
                    "message",
                    "Mật khẩu xác nhận không đúng"
            );


            request.getRequestDispatcher(
                    "/views/register.jsp"
            ).forward(
                    request,
                    response
            );


            return;

        }






        // kiểm tra ID tồn tại

        if(userDAO.findById(id) != null){


            request.setAttribute(
                    "message",
                    "Username đã tồn tại"
            );


            request.getRequestDispatcher(
                    "/views/register.jsp"
            ).forward(
                    request,
                    response
            );


            return;

        }







        // kiểm tra Email tồn tại

        if(userDAO.findByEmail(email) != null){


            request.setAttribute(
                    "message",
                    "Email đã tồn tại"
            );


            request.getRequestDispatcher(
                    "/views/register.jsp"
            ).forward(
                    request,
                    response
            );


            return;

        }






        User user =
                new User(
                        id,
                        password,
                        fullname,
                        email,
                        false
                );




        userDAO.create(user);




        response.sendRedirect(
                request.getContextPath()
                        + "/login"
        );


    }


}