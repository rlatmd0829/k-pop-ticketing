package com.kpop.ticketing.presentation.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "유저 API")
public class UserController {

	@PostMapping("/{userId}/token")
	public String createToken() {
		return """
			  {
			    "token" : "fweohjdoasdjapgfdspkdo"
			  }
			""";
	}

	@GetMapping("/{userId}/balance")
	public String getBalance() {
		return """
			{
			  "amount" : "10000"
			}
			""";
	}

	@PostMapping("/{userId}/balance")
	public ResponseEntity<Void> chargeBalance() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
