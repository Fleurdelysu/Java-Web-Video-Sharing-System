package com.poly.Assignment_VuNgocKhanh_PH59462.filter;

import com.poly.Assignment_VuNgocKhanh_PH59462.entity.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebFilter({
        "/favorite",
        "/favorites",
        "/share",
        "/profile",
        "/change-password"
})
public class AuthenticationFilter implements Filter {


    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)

            throws IOException, ServletException {


        HttpServletRequest req =
                (HttpServletRequest) request;


        HttpServletResponse resp =
                (HttpServletResponse) response;



        HttpSession session =
                req.getSession();



        User user =
                (User) session.getAttribute("user");



        if (user == null) {


            resp.sendRedirect(
                    req.getContextPath()
                            + "/login"
            );


            return;

        }



        chain.doFilter(
                request,
                response
        );

    }

}