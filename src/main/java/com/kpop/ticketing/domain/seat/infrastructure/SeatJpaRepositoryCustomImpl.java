package com.kpop.ticketing.domain.seat.infrastructure;

import static com.kpop.ticketing.domain.seat.model.QSeat.*;
import static com.kpop.ticketing.domain.show.model.QShow.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.seat.enumclass.SeatStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SeatJpaRepositoryCustomImpl implements SeatJpaRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Optional<Seat> getSeat(Long showId) {
		return Optional.ofNullable(
			jpaQueryFactory.selectFrom(seat)
			.join(seat.show, show)
			.where(show.id.eq(showId))
			.fetchOne());
	}

	@Override
	public List<Seat> getSeats(Long showId) {
		return jpaQueryFactory.selectFrom(seat)
			.join(seat.show, show)
			.where(show.id.eq(showId), seat.status.eq(SeatStatus.EMPTY))
			.fetch();
	}
}
