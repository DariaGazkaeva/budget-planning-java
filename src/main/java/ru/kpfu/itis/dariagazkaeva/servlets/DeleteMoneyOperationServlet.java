package ru.kpfu.itis.dariagazkaeva.servlets;

import ru.kpfu.itis.dariagazkaeva.models.Category;
import ru.kpfu.itis.dariagazkaeva.models.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.repositories.CategoryRepository;
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
        try {
            moneyOperationId = Long.parseLong(req.getParameter("id"));
        } catch (Exception e) {
            resp.setStatus(400);
            resp.getWriter().println("Неправильный id");
            return;
        }

        MoneyOperation moneyOperation = moneyOperationRepository.findById(moneyOperationId);

        if (!moneyOperationRepository.delete(moneyOperation)) {
            resp.setStatus(403);
            resp.getWriter().println("Не ваша операция");
            return;
        }

        resp.sendRedirect(getServletContext().getContextPath() + "/profile");

    }
}
