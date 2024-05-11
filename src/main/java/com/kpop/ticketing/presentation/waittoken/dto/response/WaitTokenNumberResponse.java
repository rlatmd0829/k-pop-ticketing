package com.kpop.ticketing.presentation.waittoken.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WaitTokenNumberResponse {
    private long waitingNumber;

    private WaitTokenNumberResponse(long waitingNumber) {
        this.waitingNumber = waitingNumber;
    }

    public static WaitTokenNumberResponse from(long waitingNumber) {
        return new WaitTokenNumberResponse(waitingNumber);
    }
}
