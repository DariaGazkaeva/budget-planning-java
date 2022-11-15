package ru.kpfu.itis.dariagazkaeva.repositories;

import ru.kpfu.itis.dariagazkaeva.models.CashSaving;

import java.util.List;

public interface CashSavingRepository {

    CashSaving findById(Long id, Long authorId);

    boolean save(CashSaving cashSaving);

    List<CashSaving> findAllByAuthorId(Long id);

    boolean update(CashSaving cashSaving, Long authorId);

    boolean delete(CashSaving cashSaving, Long authorId);

}
