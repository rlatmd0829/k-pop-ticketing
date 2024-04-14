package com.kpop.ticketing.domain.wait.repository;

import java.util.List;

import com.kpop.ticketing.domain.wait.model.WaitToken;

public interface WaitTokenReaderRepository {

	List<WaitToken> getUnexpiredWaitTokens();
}
