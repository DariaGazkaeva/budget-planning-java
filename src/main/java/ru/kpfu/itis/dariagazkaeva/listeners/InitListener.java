package ru.kpfu.itis.dariagazkaeva.listeners;

import ru.kpfu.itis.dariagazkaeva.jdbc.DbDataSource;
import ru.kpfu.itis.dariagazkaeva.repositories.CashSavingRepositoryImpl;
import ru.kpfu.itis.dariagazkaeva.repositories.CategoryRepositoryImpl;
import ru.kpfu.itis.dariagazkaeva.repositories.MoneyOperationRepositoryImpl;
import ru.kpfu.itis.dariagazkaeva.repositories.UserRepositoryImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();

        Properties properties = new Properties();
        try {
            properties.load(context.getResourceAsStream("/WEB-INF/properties/db.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        properties.getProperty("");
        DataSource dataSource = new DbDataSource(
                properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password"),
                properties.getProperty("driver"));

        context.setAttribute("userRepository", new UserRepositoryImpl(dataSource));
        context.setAttribute("cashSavingRepository", new CashSavingRepositoryImpl(dataSource));
        context.setAttribute("moneyOperationRepository", new MoneyOperationRepositoryImpl(dataSource));
        context.setAttribute("categoryRepository", new CategoryRepositoryImpl(dataSource));
    }
}
