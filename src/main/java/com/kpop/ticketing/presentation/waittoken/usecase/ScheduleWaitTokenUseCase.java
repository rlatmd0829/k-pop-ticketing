package com.kpop.ticketing.presentation.waittoken.usecase;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kpop.ticketing.domain.waittoken.components.WaitTokenReader;
import com.kpop.ticketing.domain.waittoken.model.WaitToken;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ScheduleWaitTokenUseCase {
	private final WaitTokenReader waitTokenReader;

	@Scheduled(fixedDelay = 60000)
	public void execute() {
		log.info("ScheduleWaitTokenUseCase.expireWaitToken");
		waitTokenReader.getUnexpiredWaitTokens().forEach(WaitToken::processExpiredToken);
	}
}
