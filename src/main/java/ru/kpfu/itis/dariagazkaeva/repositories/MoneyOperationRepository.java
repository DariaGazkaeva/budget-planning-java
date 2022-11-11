package ru.kpfu.itis.dariagazkaeva.repositories;

import ru.kpfu.itis.dariagazkaeva.models.MoneyOperation;

import java.util.List;

public interface MoneyOperationRepository {

    MoneyOperation findById(Long id);
    boolean save(MoneyOperation moneyOperation);

    List<MoneyOperation> findAllByAuthorId(Long authorId, String start, String end, Boolean income);

    Float getSum(Long authorId, String startDay, String endDay, Boolean income);

    boolean update(MoneyOperation moneyOperation);

    boolean delete(MoneyOperation moneyOperation);
}
