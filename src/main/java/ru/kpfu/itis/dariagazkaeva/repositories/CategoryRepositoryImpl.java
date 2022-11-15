package ru.kpfu.itis.dariagazkaeva.repositories;

import ru.kpfu.itis.dariagazkaeva.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.models.Category;
import ru.kpfu.itis.dariagazkaeva.models.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.models.User;

import javax.sql.DataSource;
import java.io.IOException;
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
    public List<Category> findAllByTypeAndAuthorId(Boolean income, Long authorId) {
        List<Category> categories = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM category WHERE income = ? AND (authorId = ? OR authorid IS NULL )")) {

            statement.setBoolean(1, income);
            statement.setLong(2, authorId);

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
            throw new DbException(e);
        }
    }

    @Override
    public Category findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM category WHERE id = ?")) {

            statement.setLong(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new Category(result.getLong("id"),
                            result.getString("name"),
                            result.getLong("authorId"),
                            result.getBoolean("income"));
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public boolean save(Category category) {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO category(name, authorid, income) values (?, ?, ?) RETURNING id"))
        {
            statement.setString(1, category.getName());
            statement.setLong(2, category.getAuthorId());
            statement.setBoolean(3, category.getIncome());

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    category.setId(result.getLong("id"));
                    return true;
                }
                return false;
            }

        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public boolean deleteCategory(Category category) {
        try (PreparedStatement statement = dataSource
                .getConnection().prepareStatement("DELETE FROM category WHERE id = ? AND authorid = ?")) {

            statement.setLong(1, category.getId());
            statement.setLong(2, category.getAuthorId());

            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DbException(e);
        }
    }
}
