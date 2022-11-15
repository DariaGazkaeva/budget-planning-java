package ru.kpfu.itis.dariagazkaeva.repositories;

import ru.kpfu.itis.dariagazkaeva.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.models.User;
import ru.kpfu.itis.dariagazkaeva.utils.HashingPassword;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private DataSource dataSource;

    public UserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public boolean save(User user) {

        try {
            if (findByEmail(user.getEmail())) {
                return false;
            }
        } catch (DbException e) {
            throw new DbException(e);
        }

        user.setPassword(new HashingPassword().hash(user.getPassword()));

        try (PreparedStatement statement = dataSource.getConnection()
                .prepareStatement("INSERT INTO public.user(email, password, name) VALUES (?, ?, ?) RETURNING id")) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {
                    user.setId(result.getLong("id"));
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public User findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.user WHERE id = ?")) {

            statement.setLong(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new User(result.getLong("id"),
                            result.getString("email"),
                            result.getString("password"),
                            result.getString("name"));
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public boolean findByEmailAndPassword(User user) {

        user.setPassword(new HashingPassword().hash(user.getPassword()));

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM public.user WHERE email = ? AND password = ?")) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public boolean findByEmail(String email) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM public.user WHERE email = ?")) {

            statement.setString(1, email);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new DbException(e);
        }
    }
}
