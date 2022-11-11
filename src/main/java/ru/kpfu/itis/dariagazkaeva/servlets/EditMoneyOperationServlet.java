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
        MoneyOperation moneyOperation = moneyOperationRepository.findById(id);

        List<Category> categories = categoryRepository.findByType(moneyOperation.getIncome());
        req.setAttribute("categories", categories);
        req.setAttribute("moneyOperation", moneyOperation);

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
            resp.getWriter().println("Неправильное значение суммы");
            return;
        }
        try {
            categoryId = Long.valueOf(req.getParameter("category"));
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            resp.getWriter().println("Неправильный id категории");
            return;
        }
        try {
            id = Long.valueOf(req.getParameter("id"));
            income = moneyOperationRepository.findById(id).getIncome();
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            resp.getWriter().println("Неправильный id");
            return;
        }

        req.setAttribute("sum", sum);
        req.setAttribute("date", date);
        req.setAttribute("categoryId", categoryId);
        req.setAttribute("description", description);

        List<String> errors = validateMoneyOperationFields(date);

        if (errors.isEmpty()) {
            MoneyOperation moneyOperation = new MoneyOperation(id, userId, sum, date, categoryId, income, description);

            if (moneyOperationRepository.update(moneyOperation)) {
                resp.sendRedirect(getServletContext().getContextPath() + "/profile");
                return;
            }
            errors.add("Что-то не так");

        }
        req.setAttribute("errors", errors);
        getServletContext().getRequestDispatcher("/WEB-INF/views/editMoneyOperation.jsp").forward(req, resp);
    }

    private List<String> validateMoneyOperationFields(String date) {

        List<String> errors = new ArrayList<>();
        if (!date.matches("\\d\\d-\\d\\d-\\d\\d\\d\\d")) {
            errors.add("Дата должна быть введена в формате дд-мм-гггг");
        }
        return errors;
    }
}
