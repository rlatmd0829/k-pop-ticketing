package com.kpop.ticketing.presentation.seat.dto.response;

import com.kpop.ticketing.domain.seat.model.Seat;

import lombok.Getter;

@Getter
public class SeatListResponse {
	private Long id;
	private String seatNumber;

	public SeatListResponse(Long id, String seatNumber) {
		this.id = id;
		this.seatNumber = seatNumber;
	}

	public static SeatListResponse from(Seat seat) {
		return new SeatListResponse(seat.getId(), seat.getNumber());
	}

}
