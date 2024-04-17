package com.kpop.ticketing.domain.waittoken.components;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.waittoken.model.WaitToken;
import com.kpop.ticketing.domain.waittoken.repository.WaitTokenReaderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WaitTokenReader {
	private final WaitTokenReaderRepository waitTokenReaderRepository;

	public WaitToken getWaitTokenByUserId(Long userId) {
		return waitTokenReaderRepository.getWaitTokenByUserId(userId).orElseThrow(
				() -> new CustomException(ErrorCode.NOT_FOUND_WAIT_TOKEN)
		);
	}

	public WaitToken getWaitTokenByToken(String token) {
		return waitTokenReaderRepository.getWaitTokenByToken(token).orElseThrow(
				() -> new CustomException(ErrorCode.NOT_FOUND_WAIT_TOKEN)
		);
	}

	public List<WaitToken> getUnexpiredWaitTokens() {
		return waitTokenReaderRepository.getUnexpiredWaitTokens();
	}

	public boolean isExistWaitToken(Long userId) {
		return waitTokenReaderRepository.getWaitTokenByUserId(userId).isPresent();
	}
}
