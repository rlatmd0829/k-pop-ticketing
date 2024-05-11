package com.kpop.ticketing.presentation.waittoken.controller;

import com.kpop.ticketing.presentation.waittoken.dto.response.WaitTokenNumberResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpop.ticketing.presentation.waittoken.dto.response.WaitTokenResponse;
import com.kpop.ticketing.presentation.waittoken.usecase.GetWaitTokenUseCase;
import com.kpop.ticketing.presentation.waittoken.usecase.IssueWaitTokenUseCase;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Wait Token", description = "대기열 토큰 API")
@RequiredArgsConstructor
public class WaitTokenController {
	private final IssueWaitTokenUseCase issueWaitTokenUseCase;
	private final GetWaitTokenUseCase getWaitTokenUseCase;

	@PostMapping("/{userId}/token")
	public ResponseEntity<WaitTokenNumberResponse> issueWaitToken(
		@PathVariable Long userId
	) {
		WaitTokenNumberResponse waitTokenResponse = issueWaitTokenUseCase.execute(userId);
		return ResponseEntity.ok(waitTokenResponse);
	}

	@GetMapping("/{userId}/token")
	public ResponseEntity<WaitTokenNumberResponse> getWaitToken(
		@PathVariable Long userId
	) {
		WaitTokenNumberResponse waitTokenResponse = getWaitTokenUseCase.execute(userId);
		return ResponseEntity.ok(waitTokenResponse);
	}
}
