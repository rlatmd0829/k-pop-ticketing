package com.kpop.ticketing.presentation.waittoken.usecase;

import com.kpop.ticketing.domain.common.redis.RedisService;
import com.kpop.ticketing.presentation.waittoken.dto.response.WaitTokenNumberResponse;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kpop.ticketing.domain.waittoken.components.WaitTokenReader;
import com.kpop.ticketing.domain.waittoken.model.WaitToken;
import com.kpop.ticketing.presentation.waittoken.dto.response.WaitTokenResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetWaitTokenUseCase {
//	private final WaitTokenReader waitTokenReader;
	private final RedisService redisService;

//	public WaitTokenResponse execute(Long userId) {
//		WaitToken waitToken = waitTokenReader.getWaitTokenByUserId(userId);
//
//		List<WaitToken> unexpiredWaitTokens = waitTokenReader.getUnexpiredWaitTokens();
//
//		long ongoingCount = unexpiredWaitTokens.stream()
//			.filter(WaitToken::isOngoing)
//			.count();
//
//		long waitNumber = waitToken.getWaitingNumber(unexpiredWaitTokens.size(), ongoingCount);
//		return WaitTokenResponse.of(waitToken.getToken(), waitToken.getStatus(), waitNumber);
//	}

	public WaitTokenNumberResponse execute(Long userId) {
		Long number = redisService.getWaitNumber(userId);
		return WaitTokenNumberResponse.from(number);
	}
}
