package com.kpop.ticketing.presentation.waittoken.usecase;

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
	private final WaitTokenReader waitTokenReader;

//	@Scheduled(fixedDelay = 60000)
//	public void execute() {
//		log.info("ScheduleWaitTokenUseCase.setStatusIfTokenExpired");
//
//		List<WaitToken> unexpiredWaitTokens = waitTokenReader.getUnexpiredWaitTokens();
//
//		List<WaitToken> expiredTokens = unexpiredWaitTokens
//			.stream()
//			.filter(WaitToken::isExpired)
//			.toList();
//
//		expiredTokens.forEach(WaitToken::expire);
//		long expiredCount = expiredTokens.size();
//
//		unexpiredWaitTokens.stream()
//			.filter(WaitToken::isWaiting)
//			.limit(expiredCount)
//			.forEach(WaitToken::onGoing);
//	}
}
