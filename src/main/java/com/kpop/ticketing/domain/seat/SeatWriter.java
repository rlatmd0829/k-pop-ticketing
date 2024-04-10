package com.kpop.ticketing.domain.seat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SeatWriter {
	private final SeatRepository seatRepository;

	public void reserveSeat(Seat seat) {
		seat.reserve();
		seatRepository.save(seat);
	}
}
