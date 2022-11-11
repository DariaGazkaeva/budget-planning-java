package ru.kpfu.itis.dariagazkaeva.servlets;

import ru.kpfu.itis.dariagazkaeva.models.CashSaving;
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
        Boolean income = req.getParameter("type").equals("income");
        List<Category> categories = categoryRepository.findByType(income);
        req.setAttribute("categories", categories);
        getServletContext().getRequestDispatcher("/WEB-INF/views/editMoneyOperation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long userId = (Long) req.getSession().getAttribute("id");
        String date = req.getParameter("date");
        Boolean income = req.getParameter("type").equals("income");
        String description = req.getParameter("description");
        Long categoryId;
        Float sum;
        try {
            categoryId = Long.valueOf(req.getParameter("category"));
            sum = Float.valueOf(req.getParameter("sum"));
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            resp.getWriter().println("Неправильное значение суммы");
            return;
        }

        req.setAttribute("sum", sum);
        req.setAttribute("date", date);
        req.setAttribute("categoryId", categoryId);

        MoneyOperation moneyOperation = new MoneyOperation(userId, sum, date, categoryId, income, description);

        if (moneyOperationRepository.save(moneyOperation)) {
            resp.sendRedirect(getServletContext().getContextPath() + "/profile");
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/editMoneyOperation.jsp").forward(req, resp);
    }
}
