package ru.kpfu.itis.dariagazkaeva.servlets;

import ru.kpfu.itis.dariagazkaeva.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.models.Category;
import ru.kpfu.itis.dariagazkaeva.models.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.repositories.CategoryRepository;
import ru.kpfu.itis.dariagazkaeva.repositories.MoneyOperationRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/edit-money-operation")
public class EditMoneyOperationServlet extends HttpServlet {
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
        Long id = Long.valueOf(req.getParameter("id"));
        Long authorId = (Long) req.getSession().getAttribute("id");
        MoneyOperation moneyOperation;

        try {
            moneyOperation = moneyOperationRepository.findById(id, authorId);
        } catch (DbException e) {
            resp.setStatus(500);
            req.setAttribute("statusCode", 500);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }


        Boolean income = moneyOperation.getIncome();
        String type = income.equals(true) ? "income" : "outcome";

        List<Category> categories = categoryRepository.findAllByTypeAndAuthorId(income, authorId);
        req.setAttribute("categories", categories);
        req.setAttribute("moneyOperation", moneyOperation);
        req.setAttribute("type", type);
        req.setAttribute("authorId", authorId);

        getServletContext().getRequestDispatcher("/WEB-INF/views/editMoneyOperation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String date = req.getParameter("date");
        String description = req.getParameter("description");
        Long userId = (Long) req.getSession().getAttribute("id");
        Float sum;
        Long categoryId;
        Long id;
        Boolean income;
        try {
            sum = Float.valueOf(req.getParameter("sum"));
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            req.setAttribute("statusCode", 400);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }
        try {
            categoryId = Long.valueOf(req.getParameter("category"));
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            req.setAttribute("statusCode", 400);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }
        try {
            income = moneyOperationRepository.findById(id, userId).getIncome();
        } catch (DbException e) {
            resp.setStatus(500);
            req.setAttribute("statusCode", 500);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("sum", sum);
        req.setAttribute("date", date);
        req.setAttribute("categoryId", categoryId);
        req.setAttribute("description", description);

        MoneyOperation moneyOperation = new MoneyOperation(id, userId, sum, date, categoryId, income, description);

        try {
            if (moneyOperationRepository.update(moneyOperation, userId)) {
                String type = income.equals(true) ? "income" : "outcome";
                resp.sendRedirect(getServletContext().getContextPath() + "/history?type=" + type);
                return;
            }
            getServletContext().getRequestDispatcher("/WEB-INF/views/editMoneyOperation.jsp").forward(req, resp);
        } catch (DbException e) {
            resp.setStatus(500);
            req.setAttribute("statusCode", 500);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
    }
}
