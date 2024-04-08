package com.kpop.ticketing.domain.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
	/* 400 BAD_REQUEST */
	INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST),

	/* 404 NOT_FOUND */
	NOT_FOUND_SHOW(HttpStatus.NOT_FOUND),
	NOT_FOUND_SEAT(HttpStatus.NOT_FOUND),

	/* 409 CONFLICT */

	/* 500 INTERNAL_SERVER_ERROR */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);

	private final HttpStatus httpStatus;

	ErrorCode(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
