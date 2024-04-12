package com.kpop.ticketing.domain.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
	/* 400 BAD_REQUEST */
	INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "잘못된 입력값 입니다."),

	/* 404 NOT_FOUND */
	NOT_FOUND_SHOW(HttpStatus.NOT_FOUND, "존재하지 않는 공연입니다."),
	NOT_FOUND_SEAT(HttpStatus.NOT_FOUND, "존재하지 않는 좌석입니다."),
	NOT_FOUND_USER(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),

	/* 409 CONFLICT */

	/* 500 INTERNAL_SERVER_ERROR */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 오류가 발생했습니다.");

	private final HttpStatus httpStatus;
	private final String message;

	ErrorCode(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}
}
