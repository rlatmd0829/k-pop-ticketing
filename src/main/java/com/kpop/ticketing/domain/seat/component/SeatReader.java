package com.kpop.ticketing.domain.seat.component;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.seat.repository.SeatReaderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatReader {
	private final SeatReaderRepository seatReaderRepository;

	public Seat getSeat(Long seatId) {
		return seatReaderRepository.getSeat(seatId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_SEAT));
	}

	public List<Seat> getSeats(Long showId) {
		return seatReaderRepository.getSeats(showId);
	}
}
