package com.kpop.ticketing.presentation.waittoken.dto.response;

import com.kpop.ticketing.domain.waittoken.model.WaitingStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WaitTokenResponse {
	private String token;
	private WaitingStatus waitingStatus;
	private long waitingNumber;

	private WaitTokenResponse(String token, WaitingStatus waitingStatus, long waitingNumber) {
		this.token = token;
		this.waitingStatus = waitingStatus;
		this.waitingNumber = waitingNumber;
	}

	public static WaitTokenResponse of(String token, WaitingStatus waitingStatus, long waitingNumber) {
		return new WaitTokenResponse(token, waitingStatus, waitingNumber);
	}
}
