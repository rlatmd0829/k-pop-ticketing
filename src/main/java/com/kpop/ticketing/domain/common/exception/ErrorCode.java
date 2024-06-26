package com.kpop.ticketing.domain.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
	/* 400 BAD_REQUEST */
	INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "잘못된 입력값 입니다."),
	INVALID_NEGATIVE_CHARGE_AMOUNT(HttpStatus.BAD_REQUEST, "충전 금액은 0원 이상이어야 합니다."),
	INVALID_NEGATIVE_WITHDRAW_AMOUNT(HttpStatus.BAD_REQUEST, "출금 금액은 0원 이상이어야 합니다."),
	INVALID_BALANCE(HttpStatus.BAD_REQUEST, "잔액이 부족합니다."),
	INVALID_EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, "만료된 토큰입니다."),
	INVALID_STATUS_TOKEN(HttpStatus.BAD_REQUEST, "유효하지 않은 토큰 상태입니다."),


	/* 404 NOT_FOUND */
	NOT_FOUND_SHOW(HttpStatus.NOT_FOUND, "존재하지 않는 공연입니다."),
	NOT_FOUND_SEAT(HttpStatus.NOT_FOUND, "존재하지 않는 좌석입니다."),
	NOT_FOUND_USER(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
	NOT_FOUND_WAIT_TOKEN(HttpStatus.NOT_FOUND, "해당 사용자의 대기 토큰이 존재하지 않습니다."),
	NOT_FOUND_RESERVATION(HttpStatus.NOT_FOUND, "존재하지 않는 예약입니다."),
	NOT_FOUND_PAYMENT(HttpStatus.NOT_FOUND, "존재하지 않는 결제입니다."),

	/* 409 CONFLICT */
	DUPLICATED_WAIT_TOKEN(HttpStatus.CONFLICT, "이미 대기 토큰이 존재합니다."),
	DUPLICATED_RESERVATION_SEAT(HttpStatus.CONFLICT, "이미 예약된 좌석입니다."),

	/* 500 INTERNAL_SERVER_ERROR */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 오류가 발생했습니다.");

	private final HttpStatus httpStatus;
	private final String message;

	ErrorCode(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}
}
