package ru.kpfu.itis.dariagazkaeva.servlets;

import ru.kpfu.itis.dariagazkaeva.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.models.Category;
import ru.kpfu.itis.dariagazkaeva.repositories.CategoryRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-category")
public class DeleteCategoryServlet extends HttpServlet {
    private CategoryRepository categoryRepository;

    @Override
    public void init() throws ServletException {
        this.categoryRepository = (CategoryRepository) getServletContext().getAttribute("categoryRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("id");

        Long categoryId;
        try {
            categoryId = Long.parseLong(req.getParameter("id"));
        } catch (Exception e) {
            resp.setStatus(400);
            req.setAttribute("statusCode", 400);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }

        Category category = new Category(categoryId, userId);

        try {
            if (!categoryRepository.deleteCategory(category)) {
                resp.setStatus(403);
                req.setAttribute("statusCode", 400);
                getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
                return;
            }
        } catch (DbException e) {
            resp.setStatus(403);
            req.setAttribute("statusCode", 400);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect(getServletContext().getContextPath() + "/profile");

    }
}
