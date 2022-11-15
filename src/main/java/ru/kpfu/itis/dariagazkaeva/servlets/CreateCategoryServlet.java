package ru.kpfu.itis.dariagazkaeva.servlets;

import ru.kpfu.itis.dariagazkaeva.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.models.Category;
import ru.kpfu.itis.dariagazkaeva.repositories.CategoryRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-category")
public class CreateCategoryServlet extends HttpServlet {

    private CategoryRepository categoryRepository;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.categoryRepository = (CategoryRepository) context.getAttribute("categoryRepository");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String incomeString = req.getParameter("income");

        resp.setContentType("application/json");

        if (name == null || name.isEmpty() || incomeString == null) {
            resp.setStatus(400);
            req.setAttribute("statusCode", 400);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }

        Boolean income;
        try {
            income = Boolean.parseBoolean(incomeString);
        } catch (Exception e) {
            resp.setStatus(400);
            req.setAttribute("statusCode", 400);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }

        Long userId = (Long) req.getSession().getAttribute("id");
        Category category = new Category(userId, name, income);
        try {
            if (!categoryRepository.save(category)) {
                resp.setStatus(400);
                req.setAttribute("statusCode", 400);
                getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
                return;
            }
            resp.getWriter().println("{\"id\":" + category.getId() + "}");
        } catch (DbException e) {
            resp.setStatus(400);
            req.setAttribute("statusCode", 400);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
    }
}
