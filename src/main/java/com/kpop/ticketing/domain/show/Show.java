package com.kpop.ticketing.domain.show;

import java.time.LocalDateTime;

import com.kpop.ticketing.domain.concert.Concert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "shows")
@Getter
public class Show {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name ="show_time", nullable = false)
	private LocalDateTime showTime;

	@Column(name = "capacity", nullable = false)
	private Integer capacity;

	@ManyToOne
	@JoinColumn(name = "concert_id")
	private Concert concert;
}
