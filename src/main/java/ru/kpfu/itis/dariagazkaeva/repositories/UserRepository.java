package ru.kpfu.itis.dariagazkaeva.repositories;

import ru.kpfu.itis.dariagazkaeva.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    boolean save(User user);

    Optional<User> findById(Long id);

    void update(User user);

    void delete(Long id);

    boolean findByEmail(User user);

}
