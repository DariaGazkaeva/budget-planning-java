package ru.kpfu.itis.dariagazkaeva.servlets;

import ru.kpfu.itis.dariagazkaeva.models.Category;
import ru.kpfu.itis.dariagazkaeva.models.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.models.User;
import ru.kpfu.itis.dariagazkaeva.repositories.CashSavingRepository;
import ru.kpfu.itis.dariagazkaeva.repositories.CategoryRepository;
import ru.kpfu.itis.dariagazkaeva.repositories.MoneyOperationRepository;
import ru.kpfu.itis.dariagazkaeva.repositories.UserRepository;
import ru.kpfu.itis.dariagazkaeva.utils.GettingDay;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {

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

        GettingDay gettingDay = new GettingDay();
        Long authorId = (Long) req.getSession().getAttribute("id");
        Boolean income = req.getParameter("type").equals("income");

        List<MoneyOperation> moneyOperations;
        List<Category> categories = new ArrayList<>();

        moneyOperations = moneyOperationRepository.findAllByAuthorId(authorId,
                gettingDay.getDayOfCurrrentMonth(true),
                gettingDay.getDayOfCurrrentMonth(false),
                income);

        for (MoneyOperation operation : moneyOperations) {
            categories.add(categoryRepository.findById(operation.getCategoryId()));
        }

        req.setAttribute("categories", categories);
        req.setAttribute("moneyOperations", moneyOperations);
        getServletContext().getRequestDispatcher("/WEB-INF/views/history.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long authorId = (Long) req.getSession().getAttribute("id");
        Boolean income = req.getParameter("type").equals("income");
        String startDay = req.getParameter("start");
        String endDay = req.getParameter("end");

        List<String> errors = validateDate(startDay, endDay);

        List<MoneyOperation> moneyOperations = new ArrayList<>();
        List<Category> categories = new ArrayList<>();

        if (errors.isEmpty()) {
            moneyOperations = moneyOperationRepository.findAllByAuthorId(authorId, startDay, endDay, income);
        } else {
            req.setAttribute("errors", errors);
        }

        for (MoneyOperation operation : moneyOperations) {
            categories.add(categoryRepository.findById(operation.getCategoryId()));
        }

        req.setAttribute("categories", categories);
        req.setAttribute("moneyOperations", moneyOperations);
        getServletContext().getRequestDispatcher("/WEB-INF/views/history.jsp").forward(req, resp);
    }

    private List<String> validateDate(String startDay, String endDay) {
        List<String> errors = new ArrayList<>();
        String pattern = "\\d\\d-\\d\\d-\\d\\d\\d\\d";
        if (startDay.matches(pattern) && endDay.matches(pattern)) {
            errors.add("Дата должна быть в формате дд-мм-гггг");
        }
        return errors;
    }
}
