package com.kpop.ticketing.presentation.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpop.ticketing.presentation.user.dto.UserBalanceResponse;
import com.kpop.ticketing.presentation.user.usecase.GetUserBalanceUseCase;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "유저 API")
@RequiredArgsConstructor
public class UserController {
	private final GetUserBalanceUseCase getUserBalanceUseCase;

	@PostMapping("/token")
	public String createToken() {
		return """
			  {
			    "token" : "fweohjdoasdjapgfdspkdo"
			  }
			""";
	}

	@GetMapping("/{userId}/balance")
	public ResponseEntity<UserBalanceResponse> getBalance(
		@PathVariable Long userId
	) {
		return ResponseEntity.ok(getUserBalanceUseCase.execute(userId));
	}

	@PostMapping("/{userId}/balance")
	public ResponseEntity<Void> chargeBalance() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
