package ru.kpfu.itis.dariagazkaeva.repositories;

import ru.kpfu.itis.dariagazkaeva.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.models.CashSaving;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CashSavingRepositoryImpl implements CashSavingRepository {
    private DataSource dataSource;

    public CashSavingRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public CashSaving findById(Long id, Long authorId) {

        CashSaving cashSaving = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM cashsaving WHERE id = ? AND authorId = ?")) {

            statement.setLong(1, id);
            statement.setLong(2, authorId);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    cashSaving = new CashSaving(result.getLong("id"),
                            result.getString("name"),
                            result.getLong("authorId"),
                            result.getFloat("sum"));
                }
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return cashSaving;
    }

    @Override
    public boolean save(CashSaving cashSaving) {

        try (PreparedStatement statement = dataSource.getConnection().prepareStatement
                ("INSERT INTO cashsaving(name, authorid, sum) VALUES (?, ?, ?) RETURNING id")) {

            statement.setString(1, cashSaving.getName());
            statement.setLong(2, cashSaving.getAuthorId());
            statement.setFloat(3, cashSaving.getSum());

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                cashSaving.setId(result.getLong("id"));
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public List<CashSaving> findAllByAuthorId(Long id) {

        List<CashSaving> cashSavings = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM cashsaving WHERE authorId = ?")) {

            statement.setLong(1, id);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    cashSavings.add(new CashSaving(result.getLong("id"),
                            result.getString("name"),
                            result.getLong("authorId"),
                            result.getFloat("sum")));
                }
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return cashSavings;
    }

    @Override
    public boolean update(CashSaving cashSaving, Long authorId) {

        try (PreparedStatement statement = dataSource.getConnection().prepareStatement
                ("UPDATE cashsaving SET name = ?, sum = ?::float WHERE id = ? AND authorId = ?")) {

            statement.setString(1, cashSaving.getName());
            statement.setFloat(2, cashSaving.getSum());
            statement.setLong(3, cashSaving.getId());
            statement.setLong(4, authorId);

            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public boolean delete(CashSaving cashSaving, Long authorId) {
        try (PreparedStatement statement = dataSource
                .getConnection().prepareStatement("DELETE FROM cashsaving WHERE id = ? AND authorid = ?")) {

            statement.setLong(1, cashSaving.getId());
            statement.setLong(2, authorId);

            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DbException(e);
        }
    }
}
