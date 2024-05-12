package com.kpop.ticketing.integration.presentation.waittoken.usecase;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

import com.kpop.ticketing.domain.common.redis.RedisService;
import com.kpop.ticketing.presentation.waittoken.dto.response.WaitTokenNumberResponse;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.user.components.UserReader;
import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.domain.waittoken.components.WaitTokenReader;
import com.kpop.ticketing.domain.waittoken.components.WaitTokenStore;
import com.kpop.ticketing.domain.waittoken.model.WaitToken;
import com.kpop.ticketing.domain.waittoken.model.WaitingStatus;
import com.kpop.ticketing.presentation.waittoken.dto.response.WaitTokenResponse;
import com.kpop.ticketing.presentation.waittoken.usecase.IssueWaitTokenUseCase;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;

@ActiveProfiles("test")
@SpringBootTest
@Sql(scripts = {
	"classpath:data/users.sql",
	"classpath:data/concerts.sql",
	"classpath:data/shows.sql",
	"classpath:data/seats.sql",
	"classpath:data/wait_tokens.sql"
})
@Transactional
class IssueWaitTokenUseCaseTest {

	@Autowired
	private IssueWaitTokenUseCase issueWaitTokenUseCase;

	@Autowired
	private RedisService redisService;

	@BeforeEach
	void setUp() {
		redisService.addToSortedSet(1L, 0);
	}

	@Test
	@DisplayName("대기 토큰 발급 테스트")
	void issueWaitTokenTest() {
		// given
		Long userId = 2L;
		WaitTokenNumberResponse waitTokenNumberResponse = issueWaitTokenUseCase.execute(userId);

		// then
		assertThat(waitTokenNumberResponse).isNotNull();
		assertThat(waitTokenNumberResponse.getWaitingNumber()).isEqualTo(1);
	}

	@Test
	@DisplayName("대기 토큰 발급 테스트 - 이미 대기 토큰이 존재하는 경우")
	void issueWaitToken_whenWaitTokenAlreadyExists() {
		// given
		Long userId = 1L;

		// then
		assertThatThrownBy(() -> issueWaitTokenUseCase.execute(userId))
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.DUPLICATED_WAIT_TOKEN.getMessage());
	}
}
