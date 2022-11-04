package ru.kpfu.itis.dariagazkaeva.jdbc;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class DbDataSource implements DataSource {

    private Connection connection;

    private final String url;
    private final String username;
    private final String password;

    public DbDataSource(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

        openConnection();
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            openConnection();
        }
        return connection;
    }

    private void openConnection() {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new NotImplementedException();
    }
}
