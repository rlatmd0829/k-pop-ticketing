package com.kpop.ticketing.presentation.waittoken.dto.response;

import com.kpop.ticketing.domain.wait.model.WaitingStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WaitTokenResponse {
	private String tokenUUID;
	private WaitingStatus waitingStatus;
	private Integer waitingNumber;

	private WaitTokenResponse(String tokenUUID, WaitingStatus waitingStatus, Integer waitingNumber) {
		this.tokenUUID = tokenUUID;
		this.waitingStatus = waitingStatus;
		this.waitingNumber = waitingNumber;
	}

	public static WaitTokenResponse of(String tokenUUID, WaitingStatus waitingStatus, Integer waitingNumber) {
		return new WaitTokenResponse(tokenUUID, waitingStatus, waitingNumber);
	}
}
