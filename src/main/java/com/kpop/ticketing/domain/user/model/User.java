package com.kpop.ticketing.domain.user.model;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "balance", nullable = false)
	private Integer balance;

	private User(String name, Integer balance) {
		this.name = name;
		this.balance = balance;
	}

	public static User create(String name, Integer balance) {
		return new User(name, balance);
	}

	public void chargeBalance(Integer chargeAmount) {
		if (chargeAmount < 0) {
			throw new CustomException(ErrorCode.INVALID_NEGATIVE_CHARGE_AMOUNT);
		}
		this.balance += chargeAmount;
	}
}
