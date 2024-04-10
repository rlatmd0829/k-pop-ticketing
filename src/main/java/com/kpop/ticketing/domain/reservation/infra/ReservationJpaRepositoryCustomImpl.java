package com.kpop.ticketing.domain.reservation.infra;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReservationJpaRepositoryCustomImpl implements ReservationJpaRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;


}
