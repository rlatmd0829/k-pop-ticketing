package com.kpop.ticketing.presentation.waittoken.usecase;

import org.springframework.stereotype.Component;

import com.kpop.ticketing.domain.wait.components.WaitTokenReader;
import com.kpop.ticketing.domain.wait.model.WaitToken;
import com.kpop.ticketing.presentation.waittoken.dto.response.WaitTokenResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetWaitTokenUseCase {
	private final WaitTokenReader waitTokenReader;

	public WaitTokenResponse execute(Long userId) {
		WaitToken waitToken = waitTokenReader.getWaitToken(userId);
		return WaitTokenResponse.of(waitToken.getTokenUUID(), waitToken.getStatus(), waitToken.getNumber());
	}
}
