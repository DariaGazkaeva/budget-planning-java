package ru.kpfu.itis.dariagazkaeva.servlets;

import ru.kpfu.itis.dariagazkaeva.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.models.CashSaving;
import ru.kpfu.itis.dariagazkaeva.models.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.models.User;
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
import java.util.Calendar;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UserRepository userRepository;
    private CashSavingRepository cashSavingRepository;
    private MoneyOperationRepository moneyOperationRepository;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.userRepository = (UserRepository) context.getAttribute("userRepository");
        this.cashSavingRepository = (CashSavingRepository) context.getAttribute("cashSavingRepository");
        this.moneyOperationRepository = (MoneyOperationRepository) context.getAttribute("moneyOperationRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = (Long) req.getSession().getAttribute("id");
            GettingDay gettingDay = new GettingDay();

            User user;
            List<CashSaving> cashSavings;
            Float incomeSumMonth;
            Float outcomeSumMonth;

            try {
                user = userRepository.findById(id);
                cashSavings = cashSavingRepository.findAllByAuthorId(id);
                incomeSumMonth = moneyOperationRepository.getSum(id, gettingDay.getDayOfCurrrentMonth(true), gettingDay.getDayOfCurrrentMonth(false), true);
                outcomeSumMonth = moneyOperationRepository.getSum(id, gettingDay.getDayOfCurrrentMonth(true), gettingDay.getDayOfCurrrentMonth(false), false);
            } catch (DbException e) {
                resp.setStatus(500);
                req.setAttribute("statusCode", 500);
                getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
                return;
            }

            req.setAttribute("name", user.getName());
            req.setAttribute("cashSavings", cashSavings);
            req.setAttribute("incomeSumMonth", incomeSumMonth);
            req.setAttribute("outcomeSumMonth", outcomeSumMonth);

            getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);

        } catch (RuntimeException e) {
            throw new DbException(e);
        }
    }
}
