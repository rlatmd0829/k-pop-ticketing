package com.kpop.ticketing.domain.wait.infrastucture;

import java.util.List;
import java.util.Optional;

import com.kpop.ticketing.domain.wait.model.WaitToken;

public interface WaitTokenJpaRepositoryCustom {
	Optional<WaitToken> getWaitTokenByUserId(Long userId);
	Optional<WaitToken> getWaitTokenByToken(String token);
	List<WaitToken> getUnexpiredWaitTokens();
}
