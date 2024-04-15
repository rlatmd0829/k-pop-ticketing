package com.kpop.ticketing.domain.wait.components;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.wait.model.WaitToken;
import com.kpop.ticketing.domain.wait.repository.WaitTokenStoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WaitTokenStore {
	private final WaitTokenStoreRepository waitTokenStoreRepository;

	public void save(WaitToken waitToken) {
		waitTokenStoreRepository.save(waitToken);
	}
}
