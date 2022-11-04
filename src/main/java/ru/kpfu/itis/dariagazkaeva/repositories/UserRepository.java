package ru.kpfu.itis.dariagazkaeva.repositories;

import javax.sql.DataSource;

public class UserRepository {

    private DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
