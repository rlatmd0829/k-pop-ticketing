package com.kpop.ticketing.presentation.waittoken.usecase;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.user.components.UserReader;
import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.domain.waittoken.components.WaitTokenReader;
import com.kpop.ticketing.domain.waittoken.components.WaitTokenStore;
import com.kpop.ticketing.domain.waittoken.model.WaitToken;
import com.kpop.ticketing.presentation.waittoken.dto.response.WaitTokenResponse;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
public class IssueWaitTokenUseCase {
	private final UserReader userReader;
	private final WaitTokenReader waitTokenReader;
	private final WaitTokenStore waitTokenStore;

	public WaitTokenResponse execute(Long userId) {
		User user = userReader.getUser(userId);
		String token = UUID.randomUUID().toString();

		if (waitTokenReader.isExistWaitToken(userId)) {
			throw new CustomException(ErrorCode.DUPLICATED_WAIT_TOKEN);
		}

		List<WaitToken> unexpiredWaitTokens = waitTokenReader.getUnexpiredWaitTokens();

		long ongoingCount = unexpiredWaitTokens.stream()
			.filter(WaitToken::isOngoing)
			.count();

		WaitToken waitToken = WaitToken.create(token, ongoingCount, user);
		waitTokenStore.save(waitToken);

		// 대기열이 있는 경우 대기번호를 부여
		long waitNumber = waitToken.getWaitingNumber(unexpiredWaitTokens.size(), ongoingCount);

		return WaitTokenResponse.of(token, waitToken.getStatus(), waitNumber);
	}
}
