package com.kpop.ticketing.presentation.waittoken.usecase;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.wait.components.WaitTokenReader;
import com.kpop.ticketing.domain.wait.model.WaitToken;
import com.kpop.ticketing.presentation.waittoken.dto.response.WaitTokenResponse;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class GetWaitTokenUseCaseTest {

	@InjectMocks
	private GetWaitTokenUseCase getWaitTokenUseCase;

	@Mock
	private WaitTokenReader waitTokenReader;

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
		when(waitTokenReader.getWaitTokenByUserId(userId)).thenReturn(waitToken);
		WaitTokenResponse waitTokenResponse = getWaitTokenUseCase.execute(userId);

		// then
		assertThat(waitTokenResponse).isNotNull();
		assertThat(waitTokenResponse.getToken()).isEqualTo(waitToken.getToken());
		assertThat(waitTokenResponse.getWaitingStatus()).isEqualTo(waitToken.getStatus());
		assertThat(waitTokenResponse.getWaitingNumber()).isEqualTo(waitToken.getNumber());
	}
}
