package com.poly.Assignment_VuNgocKhanh_PH59462.servlet;


import com.poly.Assignment_VuNgocKhanh_PH59462.dao.VideoDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl.VideoDAOImpl;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Video;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


@WebServlet("/video-detail")
public class VideoDetailServlet extends HttpServlet {


    private final VideoDAO videoDAO =
            new VideoDAOImpl();


    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)

            throws ServletException, IOException {


        String id =
                request.getParameter("id");


        // Không có id

        if (id == null || id.isEmpty()) {


            response.sendRedirect(
                    request.getContextPath()
                            + "/home"
            );


            return;

        }


        Video video =
                videoDAO.findById(id);


        // Không tìm thấy video

        if (video == null) {


            response.sendRedirect(
                    request.getContextPath()
                            + "/home"
            );


            return;

        }


        // Tăng lượt xem

        video.setViews(
                video.getViews() + 1
        );


        videoDAO.update(video);


        // Gửi dữ liệu sang JSP

        request.setAttribute(
                "video",
                video
        );


        // Video liên quan

        request.setAttribute(
                "relatedVideos",
                videoDAO.findRelatedVideos(id)
        );


        // Lưu lịch sử xem

        saveViewedCookie(
                request,
                response,
                id
        );


        request.getRequestDispatcher(
                "/views/video-detail.jsp"
        ).forward(
                request,
                response
        );


    }


    private void saveViewedCookie(
            HttpServletRequest request,
            HttpServletResponse response,
            String videoId) {

        Cookie oldCookie = null;

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if ("viewedVideos".equals(cookie.getName())) {

                    oldCookie = cookie;
                    break;

                }

            }

        }

        String value = videoId;

        if (oldCookie != null
                && oldCookie.getValue() != null
                && !oldCookie.getValue().isEmpty()) {

            String oldValue = oldCookie.getValue();

            // Không thêm lại video đã xem
            if (!oldValue.contains(videoId)) {

                value = oldValue + "|" + videoId;

            } else {

                value = oldValue;

            }

        }

        Cookie newCookie =
                new Cookie(
                        "viewedVideos",
                        value
                );

        newCookie.setMaxAge(
                60 * 60 * 24 * 7
        );

        newCookie.setPath("/");

        response.addCookie(newCookie);
    }


}