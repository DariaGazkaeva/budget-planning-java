package ru.kpfu.itis.dariagazkaeva.repositories;

import ru.kpfu.itis.dariagazkaeva.models.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.models.User;

import java.util.Date;
import java.util.Optional;

public interface MoneyOperationRepository {

    boolean save(MoneyOperationRepository moneyOperation);

    MoneyOperation[] findAllByAuthorId(Long id);

    MoneyOperation[] findAllByAuthorId(Long id, Date start, Date end);

    MoneyOperation[] findAllByAuthorId(Long id, Boolean income);

    Float getSum(String startDay, String endDay, Boolean income);

    void update(Long id);

    void delete(Long id);
}
