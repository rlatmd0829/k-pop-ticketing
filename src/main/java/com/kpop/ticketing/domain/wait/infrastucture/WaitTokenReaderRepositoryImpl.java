package com.kpop.ticketing.domain.wait.infrastucture;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.wait.model.WaitToken;
import com.kpop.ticketing.domain.wait.repository.WaitTokenReaderRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class WaitTokenReaderRepositoryImpl implements WaitTokenReaderRepository {
	private final WaitTokenJpaRepository waitTokenJpaRepository;

	@Override
	public Optional<WaitToken> getWaitTokenByUserId(Long userId) {
		return waitTokenJpaRepository.getWaitTokenByUserId(userId);
	}

	@Override
	public Optional<WaitToken> getWaitTokenByToken(String token) {
		return waitTokenJpaRepository.getWaitTokenByToken(token);
	}

	@Override
	public List<WaitToken> getUnexpiredWaitTokens() {
		return waitTokenJpaRepository.getUnexpiredWaitTokens();
	}

}
