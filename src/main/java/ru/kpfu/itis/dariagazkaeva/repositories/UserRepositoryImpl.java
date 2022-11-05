package ru.kpfu.itis.dariagazkaeva.repositories;

import ru.kpfu.itis.dariagazkaeva.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private DataSource dataSource;

    public UserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean save(User user) {
        try(PreparedStatement statement = dataSource.getConnection()
                .prepareStatement("INSERT INTO public.user(email, password, name) VALUES (?, ?, ?) RETURNING id")
        )
        {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user.setId(result.getLong("id"));
                return true;
            }

            return false;

        } catch (SQLException e) {
            //TODO проверить, что это исключение о нарушении уникальности, а не что-то другое, иначе выбросить исключение

            return false;
        }

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Long id) {

    }
}
