package ru.kpfu.itis.dariagazkaeva.servlets;

import ru.kpfu.itis.dariagazkaeva.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.models.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.repositories.MoneyOperationRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-money-operation")
public class DeleteMoneyOperationServlet extends HttpServlet {

    private MoneyOperationRepository moneyOperationRepository;

    @Override
    public void init() throws ServletException {
        this.moneyOperationRepository = (MoneyOperationRepository) getServletContext().getAttribute("moneyOperationRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long moneyOperationId;
        Long userId = (Long) req.getSession().getAttribute("id");

        try {
            moneyOperationId = Long.parseLong(req.getParameter("id"));
        } catch (Exception e) {
            resp.setStatus(400);
            req.setAttribute("statusCode", 400);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }

        MoneyOperation moneyOperation;
        try {
            moneyOperation = moneyOperationRepository.findById(moneyOperationId, userId);
        } catch (DbException e) {
            resp.setStatus(500);
            req.setAttribute("statusCode", 500);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }

        String type = moneyOperation.getIncome().equals(true) ? "income" : "outcome";

        try {
            if (!moneyOperationRepository.delete(moneyOperation, userId)) {
                resp.setStatus(403);
                req.setAttribute("statusCode", 403);
                getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
                return;
            }
        } catch (DbException e) {
            resp.setStatus(500);
            req.setAttribute("statusCode", 500);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }


        resp.sendRedirect(getServletContext().getContextPath() + "/history?type=" + type);

    }
}
