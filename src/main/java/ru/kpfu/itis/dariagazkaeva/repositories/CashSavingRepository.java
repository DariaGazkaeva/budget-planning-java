package ru.kpfu.itis.dariagazkaeva.repositories;

import ru.kpfu.itis.dariagazkaeva.models.CashSaving;

import java.util.List;

public interface CashSavingRepository {

    boolean save(CashSavingRepository cashSaving);

    List<CashSaving> findAllByAuthorId(Long id);

    void update(Long id);

    void delete(Long id);

}
