package com.kpop.ticketing.presentation.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpop.ticketing.presentation.user.dto.request.UserBalanceRequest;
import com.kpop.ticketing.presentation.user.dto.response.UserBalanceResponse;
import com.kpop.ticketing.presentation.user.usecase.ChargeUserBalanceUseCase;
import com.kpop.ticketing.presentation.user.usecase.GetUserBalanceUseCase;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "유저 API")
@RequiredArgsConstructor
public class UserController {
	private final GetUserBalanceUseCase getUserBalanceUseCase;
	private final ChargeUserBalanceUseCase chargeUserBalanceUseCase;


	@GetMapping("/{userId}/balance")
	public ResponseEntity<UserBalanceResponse> getBalance(
		@PathVariable Long userId
	) {
		return ResponseEntity.ok(getUserBalanceUseCase.execute(userId));
	}

	@PostMapping("/{userId}/balance")
	public ResponseEntity<Void> chargeBalance(
		@PathVariable Long userId,
		@RequestBody UserBalanceRequest userBalanceRequest
	) {
		chargeUserBalanceUseCase.execute(userId, userBalanceRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
