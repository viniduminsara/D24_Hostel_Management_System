package com.d24.util.id_generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.Query;

public class ReservationIdGenerator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor session, Object o) {
        String prefix = "Res-";

        Query<String> query = session.createQuery("SELECT r.reservationId FROM Reservation r ORDER BY r.reservationId DESC", String.class);
        query.setMaxResults(1);
        String lastReservationId = query.uniqueResult();

        int numericPart = Integer.parseInt(lastReservationId.substring(prefix.length()));

        numericPart++;

        String formattedCount = String.format("%03d", numericPart);
        return prefix + formattedCount;
    }
}
