package ru.kpfu.itis.dariagazkaeva.repositories;

import ru.kpfu.itis.dariagazkaeva.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    boolean save(User user);

    User findById(Long id);

    boolean findByEmailAndPassword(User user);

    boolean findByEmail(String email);

}
