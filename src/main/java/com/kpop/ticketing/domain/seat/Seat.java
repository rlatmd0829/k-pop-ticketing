package com.kpop.ticketing.domain.seat;

import com.kpop.ticketing.domain.seat.enumclass.SeatStatus;
import com.kpop.ticketing.domain.show.Show;

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

	@ManyToOne
	@JoinColumn(name = "show_id")
	private Show show;

	public void reserve() {
		this.status = SeatStatus.RESERVED;
	}
}
