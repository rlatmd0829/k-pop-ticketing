package com.kpop.ticketing.domain.seat.component;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.seat.repository.SeatRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatWriter {
	private final SeatRepository seatRepository;

	public void reserveSeat(Seat seat) {
		seat.reserve();
		seatRepository.save(seat);
	}
}
