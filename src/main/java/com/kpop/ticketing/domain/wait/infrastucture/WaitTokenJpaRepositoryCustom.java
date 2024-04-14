package com.kpop.ticketing.domain.wait.infrastucture;

import java.util.List;

import com.kpop.ticketing.domain.wait.model.WaitToken;

public interface WaitTokenJpaRepositoryCustom {

	List<WaitToken> getUnexpiredWaitTokens();
}
