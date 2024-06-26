package com.kpop.ticketing.domain.reservation.infrastructure;

import static com.kpop.ticketing.domain.reservation.model.QReservation.*;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.reservation.model.Reservation;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReservationJpaRepositoryCustomImpl implements ReservationJpaRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Optional<Reservation> getReservation(Long reservationId) {
		return Optional.ofNullable(
			jpaQueryFactory.selectFrom(reservation)
			.where(reservation.id.eq(reservationId))
			.fetchOne());
	}

}
