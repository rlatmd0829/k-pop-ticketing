package com.kpop.ticketing.presentation.waittoken.usecase;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kpop.ticketing.domain.user.components.UserReader;
import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.domain.wait.components.WaitTokenReader;
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

	public WaitTokenResponse execute(Long userId) {
		User user = userReader.getUser(userId);
		String tokenUUID = UUID.randomUUID().toString();

		List<WaitToken> unexpiredWaitTokens = waitTokenReader.getUnexpiredWaitTokens();

		long ongoingCount = unexpiredWaitTokens.stream()
			.filter(WaitToken::isOngoing)
			.count();

		WaitToken waitToken = WaitToken.create(tokenUUID, ongoingCount, unexpiredWaitTokens.size(), user);

		return WaitTokenResponse.of(tokenUUID, waitToken.getStatus(), waitToken.getNumber());
	}
}
