package com.kpop.ticketing.domain.seat.infra;

import static com.kpop.ticketing.domain.seat.QSeat.*;
import static com.kpop.ticketing.domain.show.QShow.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.seat.Seat;
import com.kpop.ticketing.domain.seat.enumclass.SeatStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SeatJpaRepositoryCustomImpl implements SeatJpaRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Seat> getSeats(Long showId) {
		return jpaQueryFactory.selectFrom(seat)
			.join(seat.show, show).fetchJoin()
			.where(show.id.eq(showId), seat.status.eq(SeatStatus.EMPTY))
			.fetch();
	}
}