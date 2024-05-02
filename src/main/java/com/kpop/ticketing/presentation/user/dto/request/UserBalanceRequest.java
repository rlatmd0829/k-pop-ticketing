package com.kpop.ticketing.presentation.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserBalanceRequest {
	@Schema(description = "충전할 금액", example = "10000")
	private Integer chargeAmount;

	private UserBalanceRequest(Integer chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public static UserBalanceRequest create(Integer chargeAmount) {
		return new UserBalanceRequest(chargeAmount);
	}
}
