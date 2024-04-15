package com.kpop.ticketing.domain.wait.components;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.wait.model.WaitToken;
import com.kpop.ticketing.domain.wait.repository.WaitTokenReaderRepository;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;

@ExtendWith(MockitoExtension.class)
class WaitTokenReaderTest {

	@InjectMocks
	private WaitTokenReader waitTokenReader;

	@Mock
	private WaitTokenReaderRepository waitTokenReaderRepository;

	@Test
	@DisplayName("대기 토큰 조회 테스트")
	void getWaitToken() {
		// given
		FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
			.objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
			.plugin(new JakartaValidationPlugin())
			.build();

		Long userId = 1L;

		WaitToken waitToken = fixtureMonkey.giveMeBuilder(WaitToken.class)
			.set("user.id", userId)
			.sample();

		// when
		when(waitTokenReaderRepository.getWaitTokenByUserId(anyLong())).thenReturn(Optional.of(waitToken));

		// then
		assertThat(waitTokenReader.getWaitTokenByUserId(userId)).isEqualTo(waitToken);
	}

	@Test
	@DisplayName("대기 토큰 조회 테스트 - 대기 토큰이 없을 경우")
	void getWaitToken_whenWaitTokenByUserIdNotExist() {
		// given
		Long userId = 999L;

		// when
		when(waitTokenReaderRepository.getWaitTokenByUserId(userId)).thenReturn(Optional.empty());

		// then
		assertThatThrownBy(() -> waitTokenReader.getWaitTokenByUserId(userId))
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.NOT_FOUND_WAIT_TOKEN.getMessage());
	}

}
