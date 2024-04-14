package com.kpop.ticketing.presentation.waittoken.usecase;

import static com.mysema.commons.lang.Assert.*;
import static net.jqwik.api.state.Action.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.domain.wait.components.WaitTokenReader;
import com.kpop.ticketing.domain.wait.model.WaitToken;
import com.kpop.ticketing.domain.wait.model.WaitingStatus;
import com.kpop.ticketing.presentation.waittoken.dto.response.WaitTokenResponse;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;

@ExtendWith(MockitoExtension.class)
class GetWaitTokenUseCaseTest {

	@InjectMocks
	private GetWaitTokenUseCase getWaitTokenUseCase;

	@Mock
	private WaitTokenReader waitTokenReader;

	@Test
	@DisplayName("대기 토큰 조회 테스트")
	void getWaitToken_success() {
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
		when(waitTokenReader.getWaitToken(userId)).thenReturn(waitToken);
		WaitTokenResponse waitTokenResponse = getWaitTokenUseCase.execute(userId);

		// then
		assertThat(waitTokenResponse).isNotNull();
		assertThat(waitTokenResponse.getTokenUUID()).isEqualTo(waitToken.getTokenUUID());
		assertThat(waitTokenResponse.getWaitingStatus()).isEqualTo(waitToken.getStatus());
		assertThat(waitTokenResponse.getWaitingNumber()).isEqualTo(waitToken.getNumber());
	}


	// TODO : 아하 이 테스트는 useCase에 책임이 아니다 waitTokenReader에 책임이 있다.
	@Test
	@DisplayName("대기 토큰 조회 테스트 - 대기 토큰이 없는 경우")
	void getWaitToken_fail_whenWaitTokenIsNotExist() {
		// given
		FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
			.objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
			.plugin(new JakartaValidationPlugin())
			.build();

		Long userId = 1L;

		// when
		// when(waitTokenReader.getWaitToken(userId)).thenReturn(Optional.empty());

		// then
		assertThatThrownBy(() -> getWaitTokenUseCase.execute(userId))
			.isInstanceOf(CustomException.class)
			.hasMessageContaining(ErrorCode.NOT_FOUND_WAIT_TOKEN.getMessage());
	}


}
