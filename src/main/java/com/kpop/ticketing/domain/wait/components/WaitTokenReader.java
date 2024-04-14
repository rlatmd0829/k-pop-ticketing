package com.kpop.ticketing.domain.wait.components;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.wait.model.WaitToken;
import com.kpop.ticketing.domain.wait.repository.WaitTokenReaderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WaitTokenReader {
	private final WaitTokenReaderRepository waitTokenReaderRepository;

	public WaitToken getWaitToken(Long userId) {
		return waitTokenReaderRepository.getWaitToken(userId).orElseThrow(
				() -> new CustomException(ErrorCode.NOT_FOUND_WAIT_TOKEN)
		);
	}

	public List<WaitToken> getUnexpiredWaitTokens() {
		return waitTokenReaderRepository.getUnexpiredWaitTokens();
	}

	public boolean isExistWaitToken(Long userId) {
		return waitTokenReaderRepository.getWaitToken(userId).isPresent();
	}
}
