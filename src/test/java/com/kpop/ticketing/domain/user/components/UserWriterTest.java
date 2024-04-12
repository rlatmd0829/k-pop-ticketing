package com.kpop.ticketing.domain.user.components;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import net.jqwik.api.Arbitraries;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.domain.user.repository.UserWriterRepository;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;

@ExtendWith(MockitoExtension.class)
class UserWriterTest {

	@InjectMocks
	private UserWriter userWriter;

	@Mock
	private UserWriterRepository userWriterRepository;

	private FixtureMonkey fixtureMonkey;

	@BeforeEach
	void setUp() {
		fixtureMonkey = FixtureMonkey.builder()
			.objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
			.plugin(new JakartaValidationPlugin())
			.build();
	}

	@Test
	@DisplayName("유저 잔액 충전 테스트")
	void chargeBalanceTest_success() {
		// given
		Integer chargeAmount = 1000;

		User user = fixtureMonkey.giveMeBuilder(User.class)
			.set("balance", Arbitraries.integers().between(0, 10000))
			.sample();

		// when
		Integer expectedBalance = user.getBalance() + chargeAmount;
		userWriter.chargeBalance(user, chargeAmount);

		// then
		assertEquals(expectedBalance, user.getBalance());
	}

	@Test
	@DisplayName("유저 잔액 충전 실패 테스트 - 충전 금액이 음수인 경우")
	void chargeBalanceTest_fail_whenChargeAmountIsNegative() {
		// given
		Integer chargeAmount = -1000;

		User user = fixtureMonkey.giveMeBuilder(User.class)
			.set("balance", Arbitraries.integers().between(0, 10000))
			.sample();

		// when, then
		assertThatThrownBy(() -> userWriter.chargeBalance(user, chargeAmount))
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.INVALID_NEGATIVE_CHARGE_AMOUNT.getMessage());
	}

}
