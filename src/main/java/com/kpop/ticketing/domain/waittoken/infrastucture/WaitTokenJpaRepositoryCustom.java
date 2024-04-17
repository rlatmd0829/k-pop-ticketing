package com.kpop.ticketing.domain.waittoken.infrastucture;

import java.util.List;
import java.util.Optional;

import com.kpop.ticketing.domain.waittoken.model.WaitToken;

public interface WaitTokenJpaRepositoryCustom {
	Optional<WaitToken> getWaitTokenByUserId(Long userId);
	Optional<WaitToken> getWaitTokenByToken(String token);
	List<WaitToken> getUnexpiredWaitTokens();
}
