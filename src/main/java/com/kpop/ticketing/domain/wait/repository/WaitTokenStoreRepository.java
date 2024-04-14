package com.kpop.ticketing.domain.wait.repository;

import com.kpop.ticketing.domain.wait.model.WaitToken;

public interface WaitTokenStoreRepository {
	WaitToken save(WaitToken waitToken);
}
