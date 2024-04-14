package com.kpop.ticketing.domain.wait.infrastucture;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.wait.model.WaitToken;
import com.kpop.ticketing.domain.wait.repository.WaitTokenStoreRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class WaitTokenStoreRepositoryImpl implements WaitTokenStoreRepository {
	private final WaitTokenJpaRepository waitTokenJpaRepository;

	public WaitToken save(WaitToken waitToken) {
		return waitTokenJpaRepository.save(waitToken);
	}
}
