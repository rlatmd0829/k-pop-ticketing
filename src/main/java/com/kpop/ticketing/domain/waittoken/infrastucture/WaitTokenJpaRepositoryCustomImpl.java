package com.kpop.ticketing.domain.waittoken.infrastucture;

import static com.kpop.ticketing.domain.waittoken.model.QWaitToken.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.waittoken.model.WaitToken;
import com.kpop.ticketing.domain.waittoken.model.WaitingStatus;
import com.querydsl.jpa.JPQLQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class WaitTokenJpaRepositoryCustomImpl implements WaitTokenJpaRepositoryCustom {
	private final JPQLQueryFactory queryFactory;

	@Override
	public Optional<WaitToken> getWaitTokenByUserId(Long userId) {
		return Optional.ofNullable(
			queryFactory.selectFrom(waitToken)
				.where(
					waitToken.user.id.eq(userId),
					waitToken.status.ne(WaitingStatus.EXPIRED)
				)
				.fetchOne());
	}

	@Override
	public Optional<WaitToken> getWaitTokenByToken(String token) {
		return Optional.ofNullable(
			queryFactory.selectFrom(waitToken)
				.where(
					waitToken.token.eq(token),
					waitToken.status.ne(WaitingStatus.EXPIRED)
				)
				.fetchOne());
	}

	@Override
	public List<WaitToken> getUnexpiredWaitTokens() {
		return queryFactory.selectFrom(waitToken)
			.where(waitToken.status.ne(WaitingStatus.EXPIRED))
			.fetch();
	}
}
