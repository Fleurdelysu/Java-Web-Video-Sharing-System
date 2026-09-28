package com.poly.Assignment_VuNgocKhanh_PH59462.servlet;


import com.poly.Assignment_VuNgocKhanh_PH59462.dao.FavoriteDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl.FavoriteDAOImpl;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Favorite;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.User;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;



@WebServlet("/favorites")
public class FavoritesServlet extends HttpServlet {


    private final FavoriteDAO favoriteDAO =
            new FavoriteDAOImpl();



    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)

            throws ServletException, IOException {


        HttpSession session =
                request.getSession(false);


        if (session == null
                || session.getAttribute("user") == null) {


            response.sendRedirect(
                    request.getContextPath()
                            + "/login"
            );


            return;

        }


        User user =
                (User) session.getAttribute("user");


        List<Favorite> favorites =
                favoriteDAO.findByUser(user);


        request.setAttribute(
                "favorites",
                favorites
        );


        request.getRequestDispatcher(
                "/views/favorites.jsp"
        ).forward(
                request,
                response
        );


    }

}
