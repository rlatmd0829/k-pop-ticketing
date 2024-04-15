package com.kpop.ticketing.presentation.waittoken.dto.response;

import com.kpop.ticketing.domain.wait.model.WaitingStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WaitTokenResponse {
	private String token;
	private WaitingStatus waitingStatus;
	private Integer waitingNumber;

	private WaitTokenResponse(String token, WaitingStatus waitingStatus, Integer waitingNumber) {
		this.token = token;
		this.waitingStatus = waitingStatus;
		this.waitingNumber = waitingNumber;
	}

	public static WaitTokenResponse of(String token, WaitingStatus waitingStatus, Integer waitingNumber) {
		return new WaitTokenResponse(token, waitingStatus, waitingNumber);
	}
}
