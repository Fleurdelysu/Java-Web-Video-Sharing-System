package com.poly.Assignment_VuNgocKhanh_PH59462.servlet;


import com.poly.Assignment_VuNgocKhanh_PH59462.dao.UserDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl.UserDAOImpl;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.User;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;



@WebServlet("/change-password")
public class ChangePasswordServlet extends HttpServlet {



    private final UserDAO userDAO =
            new UserDAOImpl();





    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)

            throws ServletException, IOException {



        HttpSession session =
                request.getSession(false);



        if(session == null
                || session.getAttribute("user") == null){


            response.sendRedirect(
                    request.getContextPath()
                            + "/login"
            );


            return;

        }




        request.getRequestDispatcher(
                "/views/change-password.jsp"
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



        HttpSession session =
                request.getSession(false);



        User user =
                (User) session.getAttribute("user");




        String oldPassword =
                request.getParameter("oldPassword");


        String newPassword =
                request.getParameter("newPassword");


        String confirmPassword =
                request.getParameter("confirmPassword");







        // kiểm tra mật khẩu cũ

        if(!user.getPassword().equals(oldPassword)){


            request.setAttribute(
                    "message",
                    "Mật khẩu cũ không đúng"
            );


            request.getRequestDispatcher(
                    "/views/change-password.jsp"
            ).forward(
                    request,
                    response
            );


            return;

        }






        // kiểm tra mật khẩu mới

        if(!newPassword.equals(confirmPassword)){


            request.setAttribute(
                    "message",
                    "Mật khẩu xác nhận không khớp"
            );


            request.getRequestDispatcher(
                    "/views/change-password.jsp"
            ).forward(
                    request,
                    response
            );


            return;

        }







        user.setPassword(
                newPassword
        );



        userDAO.update(
                user
        );





        session.setAttribute(
                "user",
                user
        );





        response.sendRedirect(
                request.getContextPath()
                        + "/profile"
        );


    }


}