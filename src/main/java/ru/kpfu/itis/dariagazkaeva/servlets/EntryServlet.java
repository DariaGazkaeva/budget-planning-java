package ru.kpfu.itis.dariagazkaeva.servlets;

import ru.kpfu.itis.dariagazkaeva.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.models.User;
import ru.kpfu.itis.dariagazkaeva.repositories.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/entry")
public class EntryServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.userRepository = (UserRepository) context.getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/entry.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        req.setAttribute("email", email);

        List<String> errors = validateEntryFields(email, password);

        if (errors.isEmpty()) {
            User user = new User(email, password);

            try {
                if (userRepository.findByEmailAndPassword(user)) {
                    req.getSession().setAttribute("id", user.getId());

                    resp.sendRedirect(getServletContext().getContextPath() + "/profile");
                    return;
                }
                errors.add("Неверно заполнено поле EMAIL или ПАРОЛЬ");
            } catch (DbException e) {
                errors.add("Проблема с базой данных");
            }
        }
        req.setAttribute("errors", errors);

        getServletContext().getRequestDispatcher("/WEB-INF/views/entry.jsp").forward(req, resp);
    }

    private List<String> validateEntryFields(String email, String password) {

        List<String> errors = new ArrayList<>();

        if (email == null || email.isEmpty()) {
            errors.add("Поле EMAIL должно быть заполнено");
        }
        if (password == null || password.isEmpty()) {
            errors.add("Поле ПАРОЛЬ должно быть заполнено");
        }
        return errors;
    }
}
