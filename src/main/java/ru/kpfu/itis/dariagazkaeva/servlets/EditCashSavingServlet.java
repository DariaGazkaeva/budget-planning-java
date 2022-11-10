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
        CashSaving cashSaving = cashSavingRepository.findById(id);
        req.setAttribute("cashSaving", cashSaving);

        getServletContext().getRequestDispatcher("/WEB-INF/views/editCashSaving.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Float sum = Float.valueOf(req.getParameter("sum"));
        // TODO здесь может быть NumberFormatException
        Long id = Long.valueOf(req.getParameter("id"));

        req.setAttribute("name", name);
        req.setAttribute("sum", sum);

        List<String> errors = new ArrayList<>();

        if (errors.isEmpty()) {
            CashSaving cashSaving = new CashSaving(id, name, sum);

            if (cashSavingRepository.update(cashSaving)) {
                resp.sendRedirect(getServletContext().getContextPath() + "/profile");
                return;
            }
            errors.add("Что-то не так");
        }
        req.setAttribute("errors", errors);
        getServletContext().getRequestDispatcher("/WEB-INF/views/editCashSaving.jsp").forward(req, resp);
    }
}
