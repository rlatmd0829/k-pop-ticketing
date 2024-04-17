package com.kpop.ticketing.domain.concert.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "concerts")
@Entity
@Getter
@NoArgsConstructor
public class Concert {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	private Concert(String name) {
		this.name = name;
	}

	public static Concert create(String name) {
		return new Concert(name);
	}
}
