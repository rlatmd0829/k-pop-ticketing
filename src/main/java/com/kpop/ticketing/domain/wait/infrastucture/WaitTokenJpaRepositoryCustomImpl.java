package com.kpop.ticketing.domain.wait.infrastucture;

import static com.kpop.ticketing.domain.wait.model.QWaitToken.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.wait.model.WaitToken;
import com.kpop.ticketing.domain.wait.model.WaitingStatus;
import com.querydsl.jpa.JPQLQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class WaitTokenJpaRepositoryCustomImpl implements WaitTokenJpaRepositoryCustom {
	private final JPQLQueryFactory queryFactory;

	@Override
	public List<WaitToken> getUnexpiredWaitTokens() {
		return queryFactory.selectFrom(waitToken)
			.where(waitToken.status.ne(WaitingStatus.EXPIRED))
			.fetch();
	}
}
