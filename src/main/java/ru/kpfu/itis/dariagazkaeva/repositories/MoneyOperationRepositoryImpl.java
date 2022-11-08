package ru.kpfu.itis.dariagazkaeva.repositories;

import ru.kpfu.itis.dariagazkaeva.models.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class MoneyOperationRepositoryImpl implements MoneyOperationRepository {

    private DataSource dataSource;

    public MoneyOperationRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean save(MoneyOperationRepository moneyOperation) {
        return false;
    }

    @Override
    public MoneyOperation[] findAllByAuthorId(Long id) {
        return new MoneyOperation[0];
    }

    @Override
    public MoneyOperation[] findAllByAuthorId(Long id, Date start, Date end) {
        return new MoneyOperation[0];
    }

    @Override
    public MoneyOperation[] findAllByAuthorId(Long id, Boolean income) {
        return new MoneyOperation[0];
    }

    @Override
    public Float getSum(String startDay, String endDay, Boolean income) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT SUM(sum) FROM moneyoperation WHERE income = ? AND date BETWEEN ?::Date AND ?::Date")) {

            statement.setBoolean(1, income);
            statement.setString(2, startDay);
            statement.setString(3, endDay);

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
