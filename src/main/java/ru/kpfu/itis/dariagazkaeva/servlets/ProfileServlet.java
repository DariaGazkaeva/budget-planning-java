package ru.kpfu.itis.dariagazkaeva.servlets;

import ru.kpfu.itis.dariagazkaeva.models.CashSaving;
import ru.kpfu.itis.dariagazkaeva.models.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.models.User;
import ru.kpfu.itis.dariagazkaeva.repositories.CashSavingRepository;
import ru.kpfu.itis.dariagazkaeva.repositories.MoneyOperationRepository;
import ru.kpfu.itis.dariagazkaeva.repositories.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
            User user = userRepository.findById(id);
            List<CashSaving> cashSavings = cashSavingRepository.findAllByAuthorId(id);
            Float incomeSumMonth = moneyOperationRepository.getSum(getDay(true), getDay(false), true);
            Float outcomeSumMonth = moneyOperationRepository.getSum(getDay(true), getDay(false), false);

            req.setAttribute("name", user.getName());
            req.setAttribute("cashSavings", cashSavings);
            req.setAttribute("incomeSumMonth", incomeSumMonth);
            req.setAttribute("outcomeSumMonth", outcomeSumMonth);

            getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);

        } catch (RuntimeException e) { // TODO сделать DbException
            throw new RuntimeException();
        }

    }

    private String getDay(boolean first) {

        Calendar calendar = Calendar.getInstance();

        String year = calendar.get(Calendar.YEAR) + "";
        while (year.length() < 4) {
            year = "0" + year;
        }

        String month = calendar.get(Calendar.MONTH) + 1 + "";
        if (month.length() < 2) {
            month = "0" + month;
        }

        String day;
        if (first) {
            day = "01";
        } else {
            day = calendar.get(Calendar.DATE) + "";
            if (day.length() < 2) {
                day = "0" + day;
            }
        }

        return year + "-" + month + "-" + day;
    }

}
