package ru.kpfu.itis.dariagazkaeva.servlets;

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

@WebServlet("/add-cash-saving")
public class CreateCashSavingServlet extends HttpServlet {

    private CashSavingRepository cashSavingRepository;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.cashSavingRepository = (CashSavingRepository) context.getAttribute("cashSavingRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/editCashSaving.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long userId = (Long) req.getSession().getAttribute("id");
        String name = req.getParameter("name");
        Float sum;
        try {
            sum = Float.valueOf(req.getParameter("sum"));
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            resp.getWriter().println("Неправильное значение суммы");
            return;
        }

        req.setAttribute("name", name);
        req.setAttribute("sum", sum);

        List<String> errors = validateCashSavingFields(name);

        if (errors.isEmpty()) {
            CashSaving cashSaving = new CashSaving(name, userId, sum);

            if (cashSavingRepository.save(cashSaving)) {
                resp.sendRedirect(getServletContext().getContextPath() + "/profile");
                return;
            }
            errors.add("Что-то не так");
        }
        req.setAttribute("errors", errors);
        getServletContext().getRequestDispatcher("/WEB-INF/views/editCashSaving.jsp").forward(req, resp);
    }

    private List<String> validateCashSavingFields(String name) {
        List<String> errors = new ArrayList<>();
        if (name.length() > 100) {
            errors.add("Длина имени не может превышать 100");
        }
        return errors;
    }
}
