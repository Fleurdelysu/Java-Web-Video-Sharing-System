package com.poly.Assignment_VuNgocKhanh_PH59462.servlet;

import com.poly.Assignment_VuNgocKhanh_PH59462.dao.UserDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl.UserDAOImpl;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {


    private final UserDAO userDAO = new UserDAOImpl();



    // Hiển thị trang login
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)

            throws ServletException, IOException {


        // Lấy email từ cookie Remember Me
        Cookie[] cookies = request.getCookies();


        if (cookies != null) {


            for (Cookie cookie : cookies) {


                if ("email".equals(cookie.getName())) {


                    request.setAttribute(
                            "rememberEmail",
                            cookie.getValue()
                    );


                    break;
                }

            }

        }



        request.getRequestDispatcher(
                "/views/login.jsp"
        ).forward(
                request,
                response
        );

    }





    // Xử lý đăng nhập
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)

            throws ServletException, IOException {



        String email =
                request.getParameter("email");


        String password =
                request.getParameter("password");



        User user =
                userDAO.findByEmailAndPassword(
                        email,
                        password
                );



        // Login thành công
        if (user != null) {



            HttpSession session =
                    request.getSession();



            session.setAttribute(
                    "user",
                    user
            );




            // Remember Me

            String remember =
                    request.getParameter(
                            "remember"
                    );



            if ("true".equals(remember)) {



                Cookie cookie =
                        new Cookie(
                                "email",
                                email
                        );



                // Cookie tồn tại 30 ngày
                cookie.setMaxAge(
                        60 * 60 * 24 * 30
                );


                // dùng cho toàn website
                cookie.setPath("/");



                response.addCookie(
                        cookie
                );



            } else {



                // Nếu bỏ tick Remember
                // thì xóa cookie cũ

                Cookie cookie =
                        new Cookie(
                                "email",
                                ""
                        );


                cookie.setMaxAge(0);

                cookie.setPath("/");


                response.addCookie(
                        cookie
                );


            }




            response.sendRedirect(
                    request.getContextPath()
                            + "/home"
            );



        }


        // Login thất bại
        else {



            request.setAttribute(
                    "message",
                    "Email hoặc mật khẩu không đúng"
            );



            request.getRequestDispatcher(
                    "/views/login.jsp"
            ).forward(
                    request,
                    response
            );


        }


    }

}