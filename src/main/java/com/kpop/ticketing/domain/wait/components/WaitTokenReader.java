package com.kpop.ticketing.domain.wait.components;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.wait.model.WaitToken;
import com.kpop.ticketing.domain.wait.repository.WaitTokenReaderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WaitTokenReader {
	private final WaitTokenReaderRepository waitTokenReaderRepository;

	public List<WaitToken> getUnexpiredWaitTokens() {
		return waitTokenReaderRepository.getUnexpiredWaitTokens();
	}
}
