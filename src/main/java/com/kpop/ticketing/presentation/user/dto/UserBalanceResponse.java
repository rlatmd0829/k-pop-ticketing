package com.kpop.ticketing.presentation.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserBalanceResponse {
	private Integer balance;

	private UserBalanceResponse(Integer balance) {
		this.balance = balance;
	}

	public static UserBalanceResponse from(Integer balance) {
		return new UserBalanceResponse(balance);
	}
}
