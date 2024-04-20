package com.kpop.ticketing.domain.reservation.components;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.reservation.model.Reservation;
import com.kpop.ticketing.domain.reservation.repository.ReservationReaderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationReader {
	private final ReservationReaderRepository reservationReaderRepository;

	public Reservation getReservation(Long reservationId) {
		return reservationReaderRepository.getReservation(reservationId)
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESERVATION));
	}
}
