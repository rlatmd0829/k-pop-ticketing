package com.kpop.ticketing.presentation.waittoken.usecase;

import com.kpop.ticketing.domain.common.redis.RedisService;
import com.kpop.ticketing.presentation.waittoken.dto.response.WaitTokenNumberResponse;
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
	private final RedisService redisService;

	public WaitTokenNumberResponse execute(Long userId) {
		User user = userReader.getUser(userId);

		if (redisService.isExistWaitToken(user.getId())) {
			throw new CustomException(ErrorCode.DUPLICATED_WAIT_TOKEN);
		}

		redisService.addToWaitingQueue(user.getId(), System.currentTimeMillis());
		Long waitNumber = redisService.getWaitNumber(user.getId());

		return WaitTokenNumberResponse.from(waitNumber);

	}
}
