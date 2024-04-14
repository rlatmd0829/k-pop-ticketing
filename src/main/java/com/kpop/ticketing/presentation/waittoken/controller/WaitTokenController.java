package com.kpop.ticketing.presentation.waittoken.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpop.ticketing.presentation.waittoken.dto.response.WaitTokenResponse;
import com.kpop.ticketing.presentation.waittoken.usecase.IssueWaitTokenUseCase;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Wait Token", description = "대기열 토큰 API")
@RequiredArgsConstructor
public class WaitTokenController {
	private final IssueWaitTokenUseCase issueWaitTokenUseCase;

	@PostMapping("/{userId}/token")
	public ResponseEntity<WaitTokenResponse> issueWaitToken(
		@PathVariable Long userId
	) {
		WaitTokenResponse waitTokenResponse = issueWaitTokenUseCase.execute(userId);
		return ResponseEntity.ok(waitTokenResponse);
	}
}
