package com.kpop.ticketing.domain.seat.component;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.seat.repository.SeatReaderRepository;
import com.kpop.ticketing.domain.seat.repository.SeatWriterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatWriter {
	private final SeatWriterRepository seatWriterRepository;

	public void reserveSeat(Seat seat) {
		seat.reserve();
		seatWriterRepository.save(seat);
	}
}
