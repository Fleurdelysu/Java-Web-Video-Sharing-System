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
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@WebServlet("/home")
public class HomeServlet extends HttpServlet {


    private static final int PAGE_SIZE = 6;


    private final VideoDAO videoDAO =
            new VideoDAOImpl();


    private final FavoriteDAO favoriteDAO =
            new FavoriteDAOImpl();



    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)

            throws ServletException, IOException {


        int page = parsePage(
                request.getParameter("page")
        );


        long totalItems =
                videoDAO.countActiveVideos();


        int totalPages =
                totalItems == 0
                        ? 1
                        : (int) Math.ceil(
                                (double) totalItems / PAGE_SIZE
                        );


        if (page > totalPages) {

            page = totalPages;

        }


        List<Video> videos =
                videoDAO.findActiveVideosPaged(
                        page,
                        PAGE_SIZE
                );


        request.setAttribute(
                "videos",
                videos
        );


        request.setAttribute(
                "currentPage",
                page
        );


        request.setAttribute(
                "totalPages",
                totalPages
        );


        HttpSession session =
                request.getSession(false);


        if (session != null
                && session.getAttribute("user") != null) {


            User user =
                    (User) session.getAttribute("user");


            List<Favorite> favorites =
                    favoriteDAO.findByUser(user);


            Set<String> favoriteVideoIds =
                    new HashSet<>();


            for (Favorite favorite : favorites) {

                favoriteVideoIds.add(
                        favorite.getVideo().getId()
                );

            }


            request.setAttribute(
                    "favoriteVideoIds",
                    favoriteVideoIds
            );

        }



        request.getRequestDispatcher(
                "/views/home.jsp"
        ).forward(
                request,
                response
        );


    }



    private int parsePage(String pageParam) {

        if (pageParam == null || pageParam.isBlank()) {

            return 1;

        }


        try {

            int page =
                    Integer.parseInt(pageParam);


            return Math.max(page, 1);


        } catch (NumberFormatException e) {

            return 1;

        }

    }

}
