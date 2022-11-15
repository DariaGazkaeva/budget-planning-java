package ru.kpfu.itis.dariagazkaeva.servlets;

import ru.kpfu.itis.dariagazkaeva.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.models.Category;
import ru.kpfu.itis.dariagazkaeva.models.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.repositories.CategoryRepository;
import ru.kpfu.itis.dariagazkaeva.repositories.MoneyOperationRepository;
import ru.kpfu.itis.dariagazkaeva.utils.DateValidator;
import ru.kpfu.itis.dariagazkaeva.utils.GettingDay;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        Boolean income;

        try {
            income = req.getParameter("type").equals("income");
        } catch (Exception e) {
            resp.setStatus(400);
            req.setAttribute("statusCode", 400);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }

        List<MoneyOperation> moneyOperations;
        List<Category> categories = new ArrayList<>();

        try {
            moneyOperations = moneyOperationRepository.findAllByAuthorId(authorId,
                    gettingDay.getDayOfCurrrentMonth(true),
                    gettingDay.getDayOfCurrrentMonth(false),
                    income);
        } catch (DbException e) {
            resp.setStatus(500);
            req.setAttribute("statusCode", 500);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }


        for (MoneyOperation operation : moneyOperations) {
            try {
                categories.add(categoryRepository.findById(operation.getCategoryId()));
            } catch (DbException e) {
                resp.setStatus(400);
                req.setAttribute("statusCode", 400);
                getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
                return;
            }
        }

        req.setAttribute("categories", categories);
        req.setAttribute("moneyOperations", moneyOperations);
        getServletContext().getRequestDispatcher("/WEB-INF/views/history.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long authorId;
        Boolean income;
        String startDay = req.getParameter("start");
        String endDay = req.getParameter("end");

        List<String> errors = new ArrayList<>();

        if (startDay == null || endDay == null || !new DateValidator().validateDateRange(startDay, endDay)) {
            errors.add("Поле дата заполнено неправильно");
        }

        try {
            authorId = (Long) req.getSession().getAttribute("id");
            income = req.getParameter("type").equals("income");
        } catch (Exception e) {
            resp.setStatus(400);
            req.setAttribute("statusCode", 400);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);

        } else {
            List<MoneyOperation> moneyOperations;
            List<Category> categories = new ArrayList<>();

            moneyOperations = moneyOperationRepository.findAllByAuthorId(authorId, startDay, endDay, income);

            try {
                for (MoneyOperation operation : moneyOperations) {
                    categories.add(categoryRepository.findById(operation.getCategoryId()));
                }
            } catch (DbException e) {
                errors.add("Ошибка в базе данных");
                req.setAttribute("errors", errors);
            }

            req.setAttribute("categories", categories);
            req.setAttribute("moneyOperations", moneyOperations);
            req.setAttribute("startDate", startDay);
            req.setAttribute("endDate", endDay);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/history.jsp").forward(req, resp);
    }
}
