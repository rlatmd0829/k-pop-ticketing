package com.kpop.ticketing.unit.domain.user.components;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import net.jqwik.api.Arbitraries;

import com.kpop.ticketing.domain.user.components.UserStore;
import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.domain.user.repository.UserStoreRepository;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UserStoreTest {

	@InjectMocks
	private UserStore userStore;

	@Mock
	private UserStoreRepository userStoreRepository;

	private FixtureMonkey fixtureMonkey;

	@BeforeEach
	void setUp() {
		fixtureMonkey = FixtureMonkey.builder()
			.objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
			.plugin(new JakartaValidationPlugin())
			.build();
	}

	@Test
	@DisplayName("유저 저장 테스트")
	void saveTest() {
		// given
		User user = fixtureMonkey.giveMeBuilder(User.class)
			.set("balance", Arbitraries.integers().between(0, 10000))
			.sample();

		// when
		when(userStoreRepository.save(user)).thenReturn(user);
		userStore.save(user);

		// then
		verify(userStoreRepository).save(user);
	}

	// @Test
	// @DisplayName("유저 잔액 충전 실패 테스트 - 충전 금액이 음수인 경우")
	// void chargeBalanceTest_fail_whenChargeAmountIsNegative() {
	// 	// given
	// 	Integer chargeAmount = -1000;
	//
	// 	User user = fixtureMonkey.giveMeBuilder(User.class)
	// 		.set("balance", Arbitraries.integers().between(0, 10000))
	// 		.sample();
	//
	// 	// when, then
	// 	assertThatThrownBy(() -> userStore.store(user, chargeAmount))
	// 		.isInstanceOf(CustomException.class)
	// 		.hasMessage(ErrorCode.INVALID_NEGATIVE_CHARGE_AMOUNT.getMessage());
	// }

}
