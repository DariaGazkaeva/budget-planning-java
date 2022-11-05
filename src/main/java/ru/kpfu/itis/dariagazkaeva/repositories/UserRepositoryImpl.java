package ru.kpfu.itis.dariagazkaeva.repositories;

import ru.kpfu.itis.dariagazkaeva.dto.RegisterDto;
import ru.kpfu.itis.dariagazkaeva.models.User;

import javax.sql.DataSource;

public class UserRepository {

    private DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User save(RegisterDto dto) {

    }
}
