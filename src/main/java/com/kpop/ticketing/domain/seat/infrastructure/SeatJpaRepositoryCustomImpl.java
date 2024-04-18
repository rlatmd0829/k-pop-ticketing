package com.kpop.ticketing.domain.seat.infrastructure;

import static com.kpop.ticketing.domain.seat.model.QSeat.*;
import static com.kpop.ticketing.domain.show.model.QShow.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.seat.model.SeatStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SeatJpaRepositoryCustomImpl implements SeatJpaRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Optional<Seat> getSeat(Long seatId) {
		return Optional.ofNullable(
			jpaQueryFactory.selectFrom(seat)
			.where(seat.id.eq(seatId))
			.fetchOne());
	}

	@Override
	public List<Seat> getSeatsByShowIdAndStatus(Long showId, SeatStatus status) {
		return jpaQueryFactory.selectFrom(seat)
			.join(seat.show, show)
			.where(show.id.eq(showId), seat.status.eq(status))
			.fetch();
	}

	@Override
	public List<Seat> getSeatsByStatus(SeatStatus status) {
		return jpaQueryFactory.selectFrom(seat)
			.where(seat.status.eq(status))
			.fetch();
	}
}
