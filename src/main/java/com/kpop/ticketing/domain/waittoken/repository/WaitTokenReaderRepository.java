package com.kpop.ticketing.domain.waittoken.repository;

import java.util.List;
import java.util.Optional;

import com.kpop.ticketing.domain.waittoken.model.WaitToken;

public interface WaitTokenReaderRepository {
	Optional<WaitToken> getWaitTokenByUserId(Long userId);
	Optional<WaitToken> getWaitTokenByToken(String token);
	List<WaitToken> getUnexpiredWaitTokens();
}
