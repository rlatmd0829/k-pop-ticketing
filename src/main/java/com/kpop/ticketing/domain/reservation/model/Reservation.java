package com.kpop.ticketing.domain.reservation.model;

import com.kpop.ticketing.domain.reservation.enumclass.ReservationStatus;
import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.user.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservations")
@Getter
@NoArgsConstructor
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private ReservationStatus status;

	@ManyToOne
	@JoinColumn(name = "seat_id")
	private Seat seat;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private Reservation(Seat seat, User user) {
		this.status = ReservationStatus.RESERVED;
		this.seat = seat;
		this.user = user;
	}

	public static Reservation create(Seat seat, User user) {
		return new Reservation(seat, user);
	}
}
