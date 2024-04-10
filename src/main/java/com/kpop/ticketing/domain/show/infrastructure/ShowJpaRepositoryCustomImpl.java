package com.kpop.ticketing.domain.show.infrastructure;

import static com.kpop.ticketing.domain.concert.model.QConcert.*;
import static com.kpop.ticketing.domain.show.model.QShow.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.show.model.Show;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ShowJpaRepositoryCustomImpl implements ShowJpaRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Optional<Show> getShow(Long showId) {
		return Optional.ofNullable(jpaQueryFactory.selectFrom(show)
			.where(show.id.eq(showId))
			.fetchOne());
	}

	@Override
	public List<Show> getShows(Long concertId, LocalDateTime showTime) {
		return jpaQueryFactory.selectFrom(show)
			.join(show.concert, concert)
			.where(concert.id.eq(concertId), show.showTime.gt(showTime))
			.fetch();
	}


}
