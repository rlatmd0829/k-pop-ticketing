package com.kpop.ticketing.domain.waittoken.repository;

import com.kpop.ticketing.domain.waittoken.model.WaitToken;

public interface WaitTokenStoreRepository {
	WaitToken save(WaitToken waitToken);
}
