package ru.kpfu.itis.dariagazkaeva.repositories;

import ru.kpfu.itis.dariagazkaeva.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.models.MoneyOperation;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoneyOperationRepositoryImpl implements MoneyOperationRepository {

    private DataSource dataSource;

    public MoneyOperationRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public MoneyOperation findById(Long id, Long authorId) {
        MoneyOperation moneyOperation = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM moneyoperation WHERE id = ? AND authorid = ?")) {

            statement.setLong(1, id);
            statement.setLong(2, authorId);

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
            throw new DbException(e);
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
            throw new DbException(e);
        }
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
            throw new DbException(e);
        }
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
            throw new DbException(e);
        }
    }

    @Override
    public boolean update(MoneyOperation moneyOperation, Long authorId) {
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement
                ("UPDATE moneyoperation SET categoryid = ?, sum = ?::float, date = ?::date, description = ? WHERE id = ? AND authorId = ?")) {

            statement.setLong(1, moneyOperation.getCategoryId());
            statement.setFloat(2, moneyOperation.getSum());
            statement.setString(3, moneyOperation.getDate());
            statement.setString(4, moneyOperation.getDescription());
            statement.setLong(5, moneyOperation.getId());
            statement.setLong(6, authorId);

            statement.execute();

        } catch (SQLException e) {
            throw new DbException(e);
        }
        return true;
    }

    @Override
    public boolean delete(MoneyOperation moneyOperation, Long authorId) {
        try (PreparedStatement statement = dataSource
                .getConnection().prepareStatement("DELETE FROM moneyoperation WHERE id = ? AND authorid = ?")) {

            statement.setLong(1, moneyOperation.getId());
            statement.setLong(2, authorId);

            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DbException(e);
        }
    }
}
