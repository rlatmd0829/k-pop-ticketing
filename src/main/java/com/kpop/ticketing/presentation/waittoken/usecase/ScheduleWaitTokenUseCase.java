package com.kpop.ticketing.presentation.waittoken.usecase;

import com.kpop.ticketing.domain.common.redis.RedisService;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kpop.ticketing.domain.waittoken.components.WaitTokenReader;
import com.kpop.ticketing.domain.waittoken.model.WaitToken;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ScheduleWaitTokenUseCase {
	private final RedisService redisService;

	@Scheduled(fixedDelay = 60000)
	public void execute() {
		log.info("ScheduleWaitTokenUseCase.setStatusIfTokenExpired");

		// 대기 중인 토큰 중 NUM_TOKENS_TO_ACTIVATE 개를 활성화하여 Active Tokens로 전환
		for (int i = 0; i < 10; i++) {
			String userId = redisService.popTokenFromWaitingQueue();
			if (userId != null) {
				// 대기 중인 토큰을 Active Tokens로 전환
				redisService.addToWorkingQueue(Long.valueOf(userId));
			} else {
				// 대기 중인 토큰이 더 이상 없으면 중지
				break;
			}
		}
	}
}
