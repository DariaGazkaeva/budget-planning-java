package ru.kpfu.itis.dariagazkaeva.servlets;

import ru.kpfu.itis.dariagazkaeva.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.models.Category;
import ru.kpfu.itis.dariagazkaeva.models.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.repositories.CategoryRepository;
import ru.kpfu.itis.dariagazkaeva.repositories.MoneyOperationRepository;
import ru.kpfu.itis.dariagazkaeva.utils.DateValidator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/add-money-operation")
public class CreateMoneyOperationServlet extends HttpServlet {

    private MoneyOperationRepository moneyOperationRepository;
    private CategoryRepository categoryRepository;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.moneyOperationRepository = (MoneyOperationRepository) context.getAttribute("moneyOperationRepository");
        this.categoryRepository = (CategoryRepository) context.getAttribute("categoryRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("id");
        Boolean income;
        try {
            if (req.getParameter("type").equals("income")) {
                income = true;
            } else if (req.getParameter("type").equals("outcome")) {
                income = false;
            } else {
                throw new IllegalArgumentException();
            }

        } catch (Exception e) {
            resp.setStatus(400);
            req.setAttribute("statusCode", 400);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }
        List<Category> categories;
        try {
            categories = categoryRepository.findAllByTypeAndAuthorId(income, userId);
        } catch (DbException e) {
            resp.setStatus(400);
            req.setAttribute("statusCode", 400);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("categories", categories);
        req.setAttribute("userId", userId);
        getServletContext().getRequestDispatcher("/WEB-INF/views/editMoneyOperation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long categoryId = null;
        Float sum = null;
        Long userId = (Long) req.getSession().getAttribute("id");
        String date = req.getParameter("date");
        String description = req.getParameter("description");
        Boolean income = null;

        List<String> errors = new ArrayList<>();
        if (date == null || !new DateValidator().validateDate(date)) {
            errors.add("Поле дата обязательно для заполнения");
        }

        try {
            categoryId = Long.valueOf(req.getParameter("category"));
            sum = Float.valueOf(req.getParameter("sum"));
            income = req.getParameter("type").equals("income");
        } catch (NumberFormatException e) {
            errors.add("Неправильное значение переданных параметров");
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            getServletContext().getRequestDispatcher("/WEB-INF/views/editMoneyOperation.jsp").forward(req, resp);
            return;
        }


        req.setAttribute("sum", sum);
        req.setAttribute("date", date);
        req.setAttribute("categoryId", categoryId);

        MoneyOperation moneyOperation = new MoneyOperation(userId, sum, date, categoryId, income, description);

        try {
            if (moneyOperationRepository.save(moneyOperation)) {
                resp.sendRedirect(getServletContext().getContextPath() + "/profile");
                return;
            }
            getServletContext().getRequestDispatcher("/WEB-INF/views/editMoneyOperation.jsp").forward(req, resp);
        } catch (DbException e) {
            resp.setStatus(500);
            req.setAttribute("statusCode", 500);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }

    }
}
