package com.d24.util.id_generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class ReservationIdGenerator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor session, Object o) {
        String prefix = "Res-";
        Long count = (Long) session.createQuery("SELECT COUNT(*) FROM Reservation").uniqueResult();
        String formattedCount = String.format("%03d", count + 1);
        return prefix + formattedCount;
    }
}
