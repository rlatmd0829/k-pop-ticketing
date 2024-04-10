package com.kpop.ticketing.domain.seat;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SeatReader {
	private final SeatRepository seatRepository;

	public Seat getSeat(Long seatId) {
		return seatRepository.getSeat(seatId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_SEAT));
	}

	public List<Seat> getSeats(Long showId) {
		return seatRepository.getSeats(showId);
	}
}
