package com.poly.Assignment_VuNgocKhanh_PH59462.servlet;


import com.poly.Assignment_VuNgocKhanh_PH59462.entity.User;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;



@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {



    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)

            throws ServletException, IOException {



        HttpSession session =
                request.getSession(false);




        // Chưa đăng nhập

        if(session == null
                || session.getAttribute("user") == null){


            response.sendRedirect(
                    request.getContextPath()
                            + "/login"
            );


            return;

        }




        User user =
                (User) session.getAttribute("user");




        request.setAttribute(
                "user",
                user
        );




        request.getRequestDispatcher(
                "/views/profile.jsp"
        ).forward(
                request,
                response
        );

    }

}