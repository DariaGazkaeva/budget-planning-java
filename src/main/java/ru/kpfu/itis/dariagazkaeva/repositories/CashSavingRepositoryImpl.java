package ru.kpfu.itis.dariagazkaeva.repositories;

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
    public boolean save(CashSavingRepository cashSaving) {
        return false;
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
            throw new IllegalArgumentException(e);
        }

        return cashSavings;
    }

    @Override
    public void update(Long id) {

    }

    @Override
    public void delete(Long id) {

    }
}
