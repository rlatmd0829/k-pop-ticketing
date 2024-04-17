package com.kpop.ticketing.presentation.seat.usecase;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kpop.ticketing.domain.seat.component.SeatReader;
import com.kpop.ticketing.domain.seat.model.Seat;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ScheduleSeatUseCase {
	private final SeatReader seatReader;

	@Scheduled(fixedRate = 60000)
	public void execute() {
		log.info("ScheduleSeatUseCase.resetSeatIfHoldTimeExceeded");
		seatReader.getHoldSeats().forEach(Seat::resetSeatIfHoldTimeExceeded);
	}
}
