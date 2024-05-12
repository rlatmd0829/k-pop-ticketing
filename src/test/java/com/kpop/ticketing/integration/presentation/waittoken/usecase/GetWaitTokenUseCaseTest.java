package com.kpop.ticketing.integration.presentation.waittoken.usecase;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import com.kpop.ticketing.domain.common.redis.RedisService;
import com.kpop.ticketing.presentation.waittoken.dto.response.WaitTokenNumberResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.kpop.ticketing.domain.waittoken.model.WaitingStatus;
import com.kpop.ticketing.presentation.waittoken.dto.response.WaitTokenResponse;
import com.kpop.ticketing.presentation.waittoken.usecase.GetWaitTokenUseCase;

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
class GetWaitTokenUseCaseTest {

	@Autowired
	private GetWaitTokenUseCase getWaitTokenUseCase;

	@Autowired
	private RedisService redisService;

	@BeforeEach
	void setUp() {
		redisService.addToSortedSet(1L, 0);
	}

	@Test
	@DisplayName("대기 토큰 조회 테스트")
	void getWaitTokenTest() {
		// given
		Long userId = 1L;

		WaitTokenNumberResponse waitTokenNumberResponse = getWaitTokenUseCase.execute(userId);

		// then
		assertThat(waitTokenNumberResponse).isNotNull();
		assertThat(waitTokenNumberResponse.getWaitingNumber()).isEqualTo(1);
	}
}
