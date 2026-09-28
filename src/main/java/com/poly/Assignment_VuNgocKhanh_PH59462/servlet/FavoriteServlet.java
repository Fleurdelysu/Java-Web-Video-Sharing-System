package com.poly.Assignment_VuNgocKhanh_PH59462.servlet;


import com.poly.Assignment_VuNgocKhanh_PH59462.dao.FavoriteDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.VideoDAO;

import com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl.FavoriteDAOImpl;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl.VideoDAOImpl;

import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Favorite;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.User;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Video;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;
import java.util.Date;


@WebServlet("/favorite")
public class FavoriteServlet extends HttpServlet {


    private final FavoriteDAO favoriteDAO =
            new FavoriteDAOImpl();


    private final VideoDAO videoDAO =
            new VideoDAOImpl();


    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)

            throws ServletException, IOException {


        HttpSession session =
                request.getSession(false);


        // chưa đăng nhập

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


        String videoId =
                request.getParameter("id");


        if (videoId == null || videoId.isEmpty()) {


            response.sendRedirect(
                    request.getContextPath()
                            + "/home"
            );


            return;

        }


        Video video =
                videoDAO.findById(videoId);


        if (video == null) {


            response.sendRedirect(
                    request.getContextPath()
                            + "/home"
            );


            return;

        }


        Favorite favorite =
                favoriteDAO.findByUserAndVideo(
                        user,
                        video
                );


        // LIKE

        if (favorite == null) {


            Favorite newFavorite =
                    new Favorite(
                            new Date(),
                            user,
                            video
                    );


            favoriteDAO.create(
                    newFavorite
            );

        }


        // UNLIKE

        else {


            favoriteDAO.delete(
                    favorite.getId()
            );


        }


        redirectAfterAction(
                request,
                response,
                videoId
        );


    }



    private void redirectAfterAction(
            HttpServletRequest request,
            HttpServletResponse response,
            String videoId)

            throws IOException {


        String from =
                request.getParameter("from");


        if ("home".equals(from)) {


            String page =
                    request.getParameter("page");


            String url =
                    request.getContextPath()
                            + "/home";


            if (page != null && !page.isEmpty()) {

                url += "?page=" + page;

            }


            response.sendRedirect(url);


            return;

        }


        if ("favorites".equals(from)) {


            response.sendRedirect(
                    request.getContextPath()
                            + "/favorites"
            );


            return;

        }


        response.sendRedirect(
                request.getContextPath()
                        + "/video-detail?id="
                        + videoId
        );


    }


}