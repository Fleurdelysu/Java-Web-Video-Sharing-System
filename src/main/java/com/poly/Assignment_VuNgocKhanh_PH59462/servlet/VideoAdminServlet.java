package com.poly.Assignment_VuNgocKhanh_PH59462.servlet;

import com.poly.Assignment_VuNgocKhanh_PH59462.dao.FavoriteDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.ShareDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.VideoDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl.FavoriteDAOImpl;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl.ShareDAOImpl;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl.VideoDAOImpl;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Favorite;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Share;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.User;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Video;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/videos")
public class VideoAdminServlet extends HttpServlet {

    private final VideoDAO videoDAO = new VideoDAOImpl();
    private final FavoriteDAO favoriteDAO = new FavoriteDAOImpl();
    private final ShareDAO shareDAO = new ShareDAOImpl();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Kiểm tra đăng nhập
        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
            return;
        }

        // Kiểm tra quyền Admin
        if (!((User)
                session.getAttribute("user")).getAdmin()) {

            response.sendRedirect(
                    request.getContextPath() + "/home"
            );
            return;
        }

        String id = request.getParameter("id");

        // Nếu có id -> lấy video để Edit
        if (id != null && !id.isEmpty()) {

            Video video = videoDAO.findById(id);

            request.setAttribute("video", video);
        }

        // Danh sách tất cả video
        int page = 1;

        String pageParam = request.getParameter("page");

        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }

        int pageSize = 10;

        long totalVideos = videoDAO.countVideos();

        int totalPages =
                (int) Math.ceil(
                        (double) totalVideos / pageSize
                );

        if (totalPages > 0 && page > totalPages) {
            page = totalPages;
        }

        request.setAttribute(
                "videos",
                videoDAO.findVideosPaged(page, pageSize)
        );

        request.setAttribute(
                "currentPage",
                page
        );

        request.setAttribute(
                "totalPages",
                totalPages
        );

        request.getRequestDispatcher(
                "/views/admin-video.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
            return;
        }

        User user = (User) session.getAttribute("user");

        if (!user.getAdmin()) {
            response.sendRedirect(
                    request.getContextPath() + "/home"
            );
            return;
        }


        request.setCharacterEncoding("UTF-8");


        String action = request.getParameter("action");


        String id = request.getParameter("id");

        String title = request.getParameter("title");

        String poster = request.getParameter("poster");

        String description = request.getParameter("description");


        int views = 0;

        String viewsParam = request.getParameter("views");

        if (viewsParam != null && !viewsParam.isEmpty()) {
            views = Integer.parseInt(viewsParam);
        }


        boolean active =
                "true".equals(
                        request.getParameter("active")
                );


        if ("create".equals(action)) {

            Video video = new Video();

            video.setId(id);
            video.setTitle(title);
            video.setPoster(poster);
            video.setViews(views);
            video.setDescription(description);
            video.setActive(active);

            videoDAO.create(video);
        }


        else if ("update".equals(action)) {

            Video video = videoDAO.findById(id);

            if (video != null) {

                video.setTitle(title);
                video.setPoster(poster);
                video.setViews(views);
                video.setDescription(description);
                video.setActive(active);

                videoDAO.update(video);
            }
        }

        else if ("delete".equals(action)) {

            // Kiểm tra video có tồn tại không
            Video video = videoDAO.findById(id);

            if (video != null) {

                // Xóa tất cả Favorite liên quan đến video
                List<Favorite> favorites = 
                        favoriteDAO.findByVideo(video);
                
                for (Favorite favorite : favorites) {
                    favoriteDAO.delete(favorite.getId());
                }

                // Xóa tất cả Share liên quan đến video
                List<Share> shares = 
                        shareDAO.findByVideo(video);
                
                for (Share share : shares) {
                    shareDAO.delete(share.getId());
                }

                // Xóa Video
                videoDAO.delete(id);
            }
        }


        response.sendRedirect(
                request.getContextPath()
                        + "/admin/videos"
        );
    }

    private Video getVideoFromRequest(
            HttpServletRequest request) {

        Video video = new Video();

        video.setId(
                request.getParameter("id")
        );

        video.setTitle(
                request.getParameter("title")
        );

        video.setPoster(
                request.getParameter("poster")
        );

        String views = request.getParameter("views");

        video.setViews(
                views == null || views.isEmpty()
                        ? 0
                        : Integer.parseInt(views)
        );

        video.setDescription(
                request.getParameter("description")
        );

        video.setActive(
                request.getParameter("active") != null
        );

        return video;
    }
}