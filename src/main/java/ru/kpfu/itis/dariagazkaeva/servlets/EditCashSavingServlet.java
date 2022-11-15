package ru.kpfu.itis.dariagazkaeva.servlets;

import ru.kpfu.itis.dariagazkaeva.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.models.CashSaving;
import ru.kpfu.itis.dariagazkaeva.repositories.CashSavingRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/edit-cash-saving")
public class EditCashSavingServlet extends HttpServlet {

    private CashSavingRepository cashSavingRepository;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.cashSavingRepository = (CashSavingRepository) context.getAttribute("cashSavingRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Long userId = (Long) req.getSession().getAttribute("id");
        CashSaving cashSaving = cashSavingRepository.findById(id, userId);
        req.setAttribute("cashSaving", cashSaving);

        getServletContext().getRequestDispatcher("/WEB-INF/views/editCashSaving.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long userId = (Long) req.getSession().getAttribute("id");
        String name = req.getParameter("name");
        Float sum;
        Long id;
        try {
            sum = Float.valueOf(req.getParameter("sum"));
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            req.setAttribute("statusCode", 400);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            req.setAttribute("statusCode", 400);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("name", name);
        req.setAttribute("sum", sum);

        List<String> errors = validateCashSavingFields(name);

        if (errors.isEmpty()) {
            CashSaving cashSaving = new CashSaving(id, name, sum);

            try {
                if (cashSavingRepository.update(cashSaving, userId)) {
                    resp.sendRedirect(getServletContext().getContextPath() + "/profile");
                    return;
                }
                errors.add("Нет прав на данную операцию");
            } catch (DbException e) {
                errors.add("Проблема с базой данных");
            }

        }
        req.setAttribute("errors", errors);
        getServletContext().getRequestDispatcher("/WEB-INF/views/editCashSaving.jsp").forward(req, resp);
    }

    private List<String> validateCashSavingFields(String name) {
        List<String> errors = new ArrayList<>();
        if (name.length() > 100) {
            errors.add("Длина названия не может превышать 100");
        }
        return errors;
    }
}
