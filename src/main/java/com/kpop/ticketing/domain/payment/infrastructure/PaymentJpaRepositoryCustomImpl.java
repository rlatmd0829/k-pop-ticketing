package com.kpop.ticketing.domain.payment.infrastructure;

import static com.kpop.ticketing.domain.payment.model.QPayment.payment;
import static com.kpop.ticketing.domain.reservation.model.QReservation.reservation;
import com.kpop.ticketing.domain.payment.model.Payment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentJpaRepositoryCustomImpl implements PaymentJpaRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Payment> getPayment(Long paymentId) {
        return Optional.ofNullable(
            jpaQueryFactory.selectFrom(payment)
                .where(payment.id.eq(paymentId))
                .fetchOne());
    }

    @Override
    public Optional<Payment> getPaymentByReservationId(Long reservationId) {
        return Optional.ofNullable(
            jpaQueryFactory.selectFrom(payment)
                .join(payment.reservation, reservation)
                .where(reservation.id.eq(reservationId))
                .fetchOne());
    }
}
