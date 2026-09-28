package com.poly.Assignment_VuNgocKhanh_PH59462.servlet;


import com.poly.Assignment_VuNgocKhanh_PH59462.dao.FavoriteDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.ShareDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.UserDAO;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl.FavoriteDAOImpl;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl.ShareDAOImpl;
import com.poly.Assignment_VuNgocKhanh_PH59462.dao.impl.UserDAOImpl;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Favorite;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.Share;
import com.poly.Assignment_VuNgocKhanh_PH59462.entity.User;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;
import java.util.List;



@WebServlet("/admin/users")
public class AdminUserServlet extends HttpServlet {


    private final UserDAO userDAO =
            new UserDAOImpl();

    private final FavoriteDAO favoriteDAO =
            new FavoriteDAOImpl();

    private final ShareDAO shareDAO =
            new ShareDAOImpl();


    private void add(HttpServletRequest request,
                     HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("user", null);
        request.setAttribute("mode", "add");

        request.getRequestDispatcher(
                "/views/admin/user-form.jsp"
        ).forward(request, response);
    }


    private void list(
            HttpServletRequest request,
            HttpServletResponse response)

            throws ServletException, IOException {

        // Pagination logic
        int page = 1;

        String pageParam = request.getParameter("page");

        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }

        int pageSize = 10;

        long totalUsers = userDAO.countUsers();

        int totalPages =
                (int) Math.ceil(
                        (double) totalUsers / pageSize
                );

        if (totalPages > 0 && page > totalPages) {
            page = totalPages;
        }

        List<User> users =
                userDAO.findUsersPaged(page, pageSize);

        request.setAttribute(
                "users",
                users
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
                "/views/admin/users.jsp"
        ).forward(
                request,
                response
        );


    }







    private void edit(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        if (id == null || id.trim().isEmpty()) {
            response.sendRedirect(
                    request.getContextPath()
                            + "/admin/users"
            );
            return;
        }

        User user = userDAO.findById(id);

        if (user == null) {
            response.sendRedirect(
                    request.getContextPath()
                            + "/admin/users"
            );
            return;
        }

        request.setAttribute("user", user);
        request.setAttribute("mode", "edit");

        request.getRequestDispatcher(
                "/views/admin/user-form.jsp"
        ).forward(request, response);
    }








    private void delete(
            HttpServletRequest request,
            HttpServletResponse response)

            throws IOException {

        String id =
                request.getParameter("id");

        // Check if user exists
        User user = userDAO.findById(id);

        if (user != null) {

            // Delete all Favorites of this user
            List<Favorite> favorites =
                    favoriteDAO.findByUser(user);

            for (Favorite favorite : favorites) {
                favoriteDAO.delete(favorite.getId());
            }

            // Delete all Shares of this user
            List<Share> shares =
                    shareDAO.findByUser(user);

            for (Share share : shares) {
                shareDAO.delete(share.getId());
            }

            // Delete User
            userDAO.delete(id);
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/admin/users"
        );


    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            action = "list";
        }

        switch (action) {

            case "add":
                add(request, response);
                break;

            case "edit":
                edit(request, response);
                break;

            case "delete":
                delete(request, response);
                break;

            default:
                list(request, response);
                break;
        }
    }
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)

            throws ServletException, IOException {


        String id =
                request.getParameter("id");


        String password =
                request.getParameter("password");


        String fullname =
                request.getParameter("fullname");


        String email =
                request.getParameter("email");


        boolean admin =
                request.getParameter("admin") != null;




        User user =
                new User(
                        id,
                        password,
                        fullname,
                        email,
                        admin
                );



        // Nếu đã tồn tại -> update

        if(userDAO.findById(id) != null){


            userDAO.update(user);


        }

        // Chưa có -> create

        else{


            userDAO.create(user);


        }





        response.sendRedirect(
                request.getContextPath()
                        + "/admin/users"
        );


    }

}