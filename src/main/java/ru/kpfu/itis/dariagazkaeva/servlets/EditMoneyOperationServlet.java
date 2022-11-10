package ru.kpfu.itis.dariagazkaeva.servlets;

import ru.kpfu.itis.dariagazkaeva.models.CashSaving;
import ru.kpfu.itis.dariagazkaeva.models.MoneyOperation;
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

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.moneyOperationRepository = (MoneyOperationRepository) context.getAttribute("moneyOperationRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        MoneyOperation moneyOperation = moneyOperationRepository.findById(id);
        req.setAttribute("moneyOperation", moneyOperation);

        getServletContext().getRequestDispatcher("/WEB-INF/views/editMoneyOperation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Float sum = Float.valueOf(req.getParameter("sum"));
        // TODO здесь может быть NumberFormatException
        String date = req.getParameter("date");
        Long categoryId = Long.valueOf(req.getParameter("category-money-operation"));
        Boolean income = req.getParameter("type-money-operation").equals("income");
        String description = req.getParameter("description");
        Long userId = (Long) req.getSession().getAttribute("id");

        req.setAttribute("sum", sum);
        req.setAttribute("date", date);
        req.setAttribute("categoryId", categoryId);
        req.setAttribute("description", description);
        req.setAttribute("type-money-operation", req.getParameter("type-money-operation"));

        // TODO проверки
        List<String> errors = new ArrayList<>();

        if (errors.isEmpty()) {
            MoneyOperation moneyOperation = new MoneyOperation(userId, sum, date, categoryId, income);

            if (moneyOperationRepository.save(moneyOperation)) {
                resp.sendRedirect(getServletContext().getContextPath() + "/profile");
                return;
            }
            errors.add("Что-то не так");

        }
        req.setAttribute("errors", errors);
        getServletContext().getRequestDispatcher("/WEB-INF/views/createMoneyOperation.jsp").forward(req, resp);
    }
}
