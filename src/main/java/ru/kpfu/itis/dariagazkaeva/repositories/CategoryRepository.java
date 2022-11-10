package ru.kpfu.itis.dariagazkaeva.repositories;

import ru.kpfu.itis.dariagazkaeva.models.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> findByType(Boolean income);
}
