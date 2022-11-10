package ru.kpfu.itis.dariagazkaeva.repositories;

import ru.kpfu.itis.dariagazkaeva.models.Category;
import ru.kpfu.itis.dariagazkaeva.models.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    private DataSource dataSource;

    public CategoryRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Category> findByType(Boolean income) {

        List<Category> categories = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM category WHERE income = ?")) {

            statement.setBoolean(1, income);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    categories.add(new Category(result.getLong("id"),
                            result.getString("name"),
                            result.getLong("authorId"),
                            result.getBoolean("income")));
                }
            }
            return categories;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
