package ru.kpfu.itis.dariagazkaeva.repositories;

import ru.kpfu.itis.dariagazkaeva.models.MoneyOperation;

import java.util.List;

public interface MoneyOperationRepository {

    MoneyOperation findById(Long id, Long authorId);
    boolean save(MoneyOperation moneyOperation);

    List<MoneyOperation> findAllByAuthorId(Long authorId, String start, String end, Boolean income);

    Float getSum(Long authorId, String startDay, String endDay, Boolean income);

    boolean update(MoneyOperation moneyOperation, Long authorId);

    boolean delete(MoneyOperation moneyOperation, Long authorId);
}
