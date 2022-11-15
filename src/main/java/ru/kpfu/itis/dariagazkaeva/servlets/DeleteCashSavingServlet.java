package ru.kpfu.itis.dariagazkaeva.servlets;

import ru.kpfu.itis.dariagazkaeva.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.models.CashSaving;
import ru.kpfu.itis.dariagazkaeva.repositories.CashSavingRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-cash-saving")
public class DeleteCashSavingServlet extends HttpServlet {
    private CashSavingRepository cashSavingRepository;

    @Override
    public void init() throws ServletException {
        this.cashSavingRepository = (CashSavingRepository) getServletContext().getAttribute("cashSavingRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long cashSavingId;
        Long userId = (Long) req.getSession().getAttribute("id");

        try {
            cashSavingId = Long.parseLong(req.getParameter("id"));
        } catch (Exception e) {
            resp.setStatus(400);
            req.setAttribute("statusCode", 400);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }

        CashSaving cashSaving;
        try {
            cashSaving = cashSavingRepository.findById(cashSavingId, userId);
        } catch (DbException e) {
            resp.setStatus(400);
            req.setAttribute("statusCode", 400);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }

        try {
            if (!cashSavingRepository.delete(cashSaving, userId)) {
                resp.setStatus(403);
                req.setAttribute("statusCode", 400);
                getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
                return;
            }
        } catch (DbException e) {
            resp.setStatus(403);
            req.setAttribute("statusCode", 400);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect(getServletContext().getContextPath() + "/profile");

    }
}
