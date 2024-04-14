package com.kpop.ticketing.domain.wait.repository;

import java.util.List;
import java.util.Optional;

import com.kpop.ticketing.domain.wait.model.WaitToken;

public interface WaitTokenReaderRepository {
	Optional<WaitToken> getWaitToken(Long userId);
	List<WaitToken> getUnexpiredWaitTokens();
}
