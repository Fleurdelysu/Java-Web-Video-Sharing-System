package com.poly.Assignment_VuNgocKhanh_PH59462.servlet;


import com.poly.Assignment_VuNgocKhanh_PH59462.dao.UserDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl.UserDAOImpl;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.User;
import com.poly.Assignment_VuNgocKhanh_PH59462.service.MailService;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;
import java.util.UUID;



@WebServlet("/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {


    private final UserDAO userDAO =
            new UserDAOImpl();




    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)

            throws ServletException, IOException {



        request.getRequestDispatcher(
                "/views/forgot-password.jsp"
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



        String email =
                request.getParameter("email");



        User user =
                userDAO.findByEmail(email);



        if(user == null){


            request.setAttribute(
                    "message",
                    "Email không tồn tại"
            );


            request.getRequestDispatcher(
                    "/views/forgot-password.jsp"
            ).forward(
                    request,
                    response
            );


            return;

        }




        // tạo password mới

        String newPassword =
                UUID.randomUUID()
                        .toString()
                        .substring(0,8);



        user.setPassword(
                newPassword
        );



        userDAO.update(
                user
        );





        String content =

                "Xin chào "
                        + user.getFullname()
                        + "\n\n"
                        + "Mật khẩu mới của bạn là: "
                        + newPassword
                        + "\n\n"
                        + "Vui lòng đăng nhập và đổi mật khẩu.";



        MailService.send(
                email,
                "Reset Password",
                content
        );





        request.setAttribute(
                "message",
                "Mật khẩu mới đã được gửi qua email"
        );



        request.getRequestDispatcher(
                "/views/forgot-password.jsp"
        ).forward(
                request,
                response
        );


    }

}