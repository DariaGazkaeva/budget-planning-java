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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.userRepository = (UserRepository) context.getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("password_repeat");
        String name = req.getParameter("name");

        req.setAttribute("name", name);
        req.setAttribute("email", email);

        List<String> errors = validateRegisterFields(email, password, passwordRepeat, name);

        if (errors.isEmpty()) {
            User user = new User(email, password, name);

            try {
                if (userRepository.save(user)) {

                    req.getSession().setAttribute("id", user.getId());

                    resp.sendRedirect(getServletContext().getContextPath() + "/profile");
                    return;
                }
                errors.add("Пользователь с таким EMAIL уже существует");
            } catch (DbException e) {
                errors.add("Проблема с базой данных");
            }
        }
        req.setAttribute("errors", errors);
        getServletContext().getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    private boolean validatePasswordRepeat(String password, String passwordRepeat) {
        return password.equals(passwordRepeat);
    }

    private boolean validatePasswordSymbols(String password) {
        return password.length() >= 8 && !password.matches("\\d+");

    }

    private List<String> validateRegisterFields(String email, String password, String passwordRepeat, String name) {

        List<String> errors = new ArrayList<>();

        if (name == null || name.isEmpty()) {
            errors.add("Поле ИМЯ должно быть заполнено");
        }
        if (email == null || email.isEmpty()) {
            errors.add("Поле EMAIL должно быть заполнено");
        }
        if (password == null || password.isEmpty()) {
            errors.add("Поле ПАРОЛЬ должно быть заполнено");
        }
        if (passwordRepeat == null || passwordRepeat.isEmpty()) {
            errors.add("Поле ПАРОЛЬ должно быть заполнено");
        }
        if (!validatePasswordRepeat(password, passwordRepeat)) {
            errors.add("Поле ПАРОЛЬ заполнено неверно");
        }
        if (!validatePasswordSymbols(password)) {
            errors.add("ПАРОЛЬ не должен быть менее 8 символов и состоять только из цифр");
        }
        return errors;
    }
}
