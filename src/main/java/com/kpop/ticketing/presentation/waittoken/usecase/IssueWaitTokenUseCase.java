package com.kpop.ticketing.presentation.waittoken.usecase;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.user.components.UserReader;
import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.domain.wait.components.WaitTokenReader;
import com.kpop.ticketing.domain.wait.components.WaitTokenStore;
import com.kpop.ticketing.domain.wait.model.WaitToken;
import com.kpop.ticketing.presentation.waittoken.dto.response.WaitTokenResponse;

import jakarta.transaction.Transactional;
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

		WaitToken waitToken = WaitToken.create(token, ongoingCount, unexpiredWaitTokens.size(), user);
		waitTokenStore.save(waitToken);

		return WaitTokenResponse.of(token, waitToken.getStatus(), waitToken.getNumber());
	}
}
