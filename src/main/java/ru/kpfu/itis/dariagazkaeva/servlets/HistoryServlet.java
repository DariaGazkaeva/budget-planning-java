package ru.kpfu.itis.dariagazkaeva.servlets;

import ru.kpfu.itis.dariagazkaeva.models.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.repositories.CashSavingRepository;
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
import java.util.List;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {

    private MoneyOperationRepository moneyOperationRepository;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.moneyOperationRepository = (MoneyOperationRepository) context.getAttribute("moneyOperationRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long authorId = (Long) req.getSession().getAttribute("id");
        GettingDay gettingDay = new GettingDay();

        Boolean income = req.getParameter("type").equals("income");
        List<MoneyOperation> moneyOperations;


        moneyOperations = moneyOperationRepository.findAllByAuthorId(authorId,
                gettingDay.getDayOfCurrrentMonth(true),
                gettingDay.getDayOfCurrrentMonth(false),
                income);

        req.setAttribute("moneyOperations", moneyOperations);
        getServletContext().getRequestDispatcher("/WEB-INF/views/history.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
