package ru.kpfu.itis.dariagazkaeva.repositories;

import ru.kpfu.itis.dariagazkaeva.models.CashSaving;
import ru.kpfu.itis.dariagazkaeva.models.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MoneyOperationRepositoryImpl implements MoneyOperationRepository {

    private DataSource dataSource;

    public MoneyOperationRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public MoneyOperation findById(Long id) {
        MoneyOperation moneyOperation = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM moneyoperation WHERE id = ?")) {

            statement.setLong(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    moneyOperation = new MoneyOperation(result.getLong("id"),
                            result.getLong("authorId"),
                            result.getFloat("sum"),
                            result.getString("date"),
                            result.getLong("categoryId"),
                            result.getBoolean("income"),
                            result.getString("description"));
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return moneyOperation;
    }

    @Override
    public boolean save(MoneyOperation moneyOperation) {
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement
                ("INSERT INTO moneyoperation(authorId, sum, date, categoryId, description, income) VALUES (?, ?, ?::Date, ?, ?, ?) RETURNING id")) {

            statement.setLong(1, moneyOperation.getAuthorId());
            statement.setFloat(2, moneyOperation.getSum());
            statement.setString(3, moneyOperation.getDate());
            statement.setLong(4, moneyOperation.getCategoryId());
            statement.setString(5, moneyOperation.getDescription());
            statement.setBoolean(6, moneyOperation.getIncome());

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                moneyOperation.setId(result.getLong("id"));
                return true;
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public MoneyOperation[] findAllByAuthorId(Long id) {
        return new MoneyOperation[0];
    }

    @Override
    public List<MoneyOperation> findAllByAuthorId(Long authorId, String start, String end, Boolean income) {

        List<MoneyOperation> moneyOperations = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM moneyoperation WHERE income = ? AND authorid = ? AND date BETWEEN ?::Date AND ?::Date")) {

            statement.setBoolean(1, income);
            statement.setLong(2, authorId);
            statement.setString(3, start);
            statement.setString(4, end);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    moneyOperations.add(new MoneyOperation(result.getLong("id"),
                            result.getLong("authorId"),
                            result.getFloat("sum"),
                            result.getString("date"),
                            result.getLong("categoryId"),
                            result.getBoolean("income"),
                            result.getString("description")));
                }
            }

            return moneyOperations;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public MoneyOperation[] findAllByAuthorId(Long id, Boolean income) {
        return new MoneyOperation[0];
    }

    @Override
    public Float getSum(Long authorId, String startDay, String endDay, Boolean income) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT SUM(sum) FROM moneyoperation WHERE income = ? AND authorid = ? AND date BETWEEN ?::Date AND ?::Date")) {

            statement.setBoolean(1, income);
            statement.setLong(2, authorId);
            statement.setString(3, startDay);
            statement.setString(4, endDay);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getFloat("sum");
                }
            }
            return 0f;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(Long id) {

    }

    @Override
    public void delete(Long id) {

    }
}
