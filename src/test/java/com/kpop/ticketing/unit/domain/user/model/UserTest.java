package com.kpop.ticketing.unit.domain.user.model;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.user.model.User;

class UserTest {

	@Test
	@DisplayName("잔액 충전 테스트")
	void chargeBalanceTest() {
		// given
		User user = User.create("test", 10000);

		// when
		user.chargeBalance(1000);

		// then
		assertEquals(11000, user.getBalance());
	}

	@Test
	@DisplayName("잔액 충전 테스트 - 음수 금액")
	void chargeBalanceTest_whenNegativeChargeAmount() {
		// given
		User user = User.create("test", 10000);

		// when, then
		assertThatThrownBy(() -> user.chargeBalance(-1000))
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.INVALID_NEGATIVE_CHARGE_AMOUNT.getMessage());
	}

	@Test
	@DisplayName("잔액 출금 테스트")
	void withdrawBalanceTest() {
		// given
		User user = User.create("test", 10000);

		// when
		user.withdrawBalance(1000);

		// then
		assertEquals(9000, user.getBalance());
	}

	@Test
	@DisplayName("잔액 출금 테스트 - 음수 금액")
	void withdrawBalanceTest_whenNegativeWithdrawAmount() {
		// given
		User user = User.create("test", 10000);

		// when, then
		assertThatThrownBy(() -> user.withdrawBalance(-1000))
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.INVALID_NEGATIVE_WITHDRAW_AMOUNT.getMessage());
	}

	@Test
	@DisplayName("잔액 출금 테스트 - 잔액 부족")
	void withdrawBalanceTest_whenInvalidBalance() {
		// given
		User user = User.create("test", 10000);

		// when, then
		assertThatThrownBy(() -> user.withdrawBalance(11000))
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.INVALID_BALANCE.getMessage());
	}
}
