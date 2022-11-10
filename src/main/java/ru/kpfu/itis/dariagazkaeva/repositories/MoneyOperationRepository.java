package ru.kpfu.itis.dariagazkaeva.repositories;

import ru.kpfu.itis.dariagazkaeva.models.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.models.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MoneyOperationRepository {

    MoneyOperation findById(Long id);
    boolean save(MoneyOperation moneyOperation);

    MoneyOperation[] findAllByAuthorId(Long id);

    List<MoneyOperation> findAllByAuthorId(Long authorId, String start, String end, Boolean income);

    MoneyOperation[] findAllByAuthorId(Long id, Boolean income);

    Float getSum(Long authorId, String startDay, String endDay, Boolean income);

    void update(Long id);

    void delete(Long id);
}
