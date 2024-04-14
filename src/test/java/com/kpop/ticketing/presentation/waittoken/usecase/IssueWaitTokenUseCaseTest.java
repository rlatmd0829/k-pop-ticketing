package com.kpop.ticketing.presentation.waittoken.usecase;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.user.components.UserReader;
import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.domain.wait.components.WaitTokenReader;
import com.kpop.ticketing.domain.wait.model.WaitToken;
import com.kpop.ticketing.domain.wait.model.WaitingStatus;
import com.kpop.ticketing.presentation.waittoken.dto.response.WaitTokenResponse;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;

@ExtendWith(MockitoExtension.class)
class IssueWaitTokenUseCaseTest {

	@InjectMocks
	private IssueWaitTokenUseCase issueWaitTokenUseCase;

	@Mock
	private UserReader userReader;

	@Mock
	private WaitTokenReader waitTokenReader;

	private static final int MAX_WAITING_NUMBER = 100;

	@Test
	@DisplayName("대기 토큰 발급 테스트 - 현재 활동 중인 대기 토큰이 max wating number 이상일 경우")
	void issueWaitToken_success_whenNumberOfOngoingTokensIsOverMax() {
		// given
		FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
			.objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
			.plugin(new JakartaValidationPlugin())
			.build();

		Long userId = 1L;

		User user = fixtureMonkey.giveMeBuilder(User.class)
			.set("id", userId)
			.sample();

		List<WaitToken> waitTokens = fixtureMonkey.giveMeBuilder(WaitToken.class)
			.set("user", user)
			.set("status", WaitingStatus.ONGOING)
			.sampleList(100);

		// when
		when(userReader.getUser(anyLong())).thenReturn(user);
		when(waitTokenReader.getUnexpiredWaitTokens()).thenReturn(waitTokens);
		WaitTokenResponse waitTokenResponse = issueWaitTokenUseCase.execute(userId);

		// then
		assertThat(waitTokenResponse).isNotNull();
		assertThat(waitTokenResponse.getTokenUUID()).isNotNull();
		assertThat(waitTokenResponse.getWaitingStatus()).isEqualTo(WaitingStatus.WAITING);
		assertThat(waitTokenResponse.getWaitingNumber()).isEqualTo(waitTokens.size() - MAX_WAITING_NUMBER + 1);
	}

	@Test
	@DisplayName("대기 토큰 발급 테스트 - 현재 활동 중인 대기 토큰이 max wating number 보다 적을 경우")
	void issueWaitToken_success_whenNumberOfOngoingTokensIsLessThanMax() {
		// given
		FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
			.objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
			.plugin(new JakartaValidationPlugin())
			.build();

		Long userId = 1L;

		User user = fixtureMonkey.giveMeBuilder(User.class)
			.set("id", userId)
			.sample();

		List<WaitToken> waitTokens = fixtureMonkey.giveMeBuilder(WaitToken.class)
			.set("user", user)
			.set("status", WaitingStatus.ONGOING)
			.sampleList(60);

		// when
		when(userReader.getUser(anyLong())).thenReturn(user);
		when(waitTokenReader.getUnexpiredWaitTokens()).thenReturn(waitTokens);
		WaitTokenResponse waitTokenResponse = issueWaitTokenUseCase.execute(userId);

		// then
		assertThat(waitTokenResponse).isNotNull();
		assertThat(waitTokenResponse.getTokenUUID()).isNotNull();
		assertThat(waitTokenResponse.getWaitingStatus()).isEqualTo(WaitingStatus.ONGOING);
		assertThat(waitTokenResponse.getWaitingNumber()).isEqualTo(0);
	}
}
