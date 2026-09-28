package com.poly.Assignment_VuNgocKhanh_PH59462.servlet;


import com.poly.Assignment_VuNgocKhanh_PH59462.dao.ShareDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.VideoDAO;

import com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl.ShareDAOImpl;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl.VideoDAOImpl;

import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Share;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.User;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Video;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;
import java.util.Date;



@WebServlet("/share")
public class ShareServlet extends HttpServlet {



    private final ShareDAO shareDAO =
            new ShareDAOImpl();



    private final VideoDAO videoDAO =
            new VideoDAOImpl();





    @Override
    protected void doPost(
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





        User user =
                (User) session.getAttribute("user");





        String videoId =
                request.getParameter("videoId");



        String emails =
                request.getParameter("email");

        if (emails == null || emails.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/video-detail?id="
                            + videoId
            );

            return;
        }






        Video video =
                videoDAO.findById(videoId);





        if(video == null){


            response.sendRedirect(
                    request.getContextPath()
                            + "/home"
            );


            return;

        }






        Share share =
                new Share(
                        emails,
                        new Date(),
                        user,
                        video
                );





        shareDAO.create(
                share
        );






        redirectAfterShare(
                request,
                response,
                videoId
        );


    }



    private void redirectAfterShare(
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