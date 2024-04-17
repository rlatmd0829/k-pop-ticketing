package com.kpop.ticketing.domain.seat.model;

import java.time.LocalDateTime;

import com.kpop.ticketing.domain.show.model.Show;

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
@Table(name = "seats")
@Getter
@NoArgsConstructor
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "number")
	private String number;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private SeatStatus status;

	@Column(name = "expired_at")
	private LocalDateTime holdTime;

	@ManyToOne
	@JoinColumn(name = "show_id")
	private Show show;

	public void setStatus(SeatStatus status) {
		this.status = status;
	}

	public void setHoldTime(LocalDateTime holdTime) {
		this.holdTime = holdTime;
	}

	private Seat(String number, Integer amount, SeatStatus status, LocalDateTime holdTime, Show show) {
		this.number = number;
		this.amount = amount;
		this.status = status;
		this.holdTime = holdTime;
		this.show = show;
	}

	public static Seat create(String number, Integer amount, Show show) {
		return new Seat(number, amount, SeatStatus.EMPTY, null, show);
	}

	public void hold() {
		this.status = SeatStatus.HOLD;
		this.holdTime = LocalDateTime.now().plusMinutes(5);
	}

	public void reserve() {
		this.status = SeatStatus.RESERVED;
	}

	public boolean isHoldTimeExceeded() {
		return holdTime.isBefore(LocalDateTime.now());
	}

	public void resetSeatIfHoldTimeExceeded() {
		if (isHoldTimeExceeded()) {
			setStatus(SeatStatus.EMPTY);
		}
	}
}
