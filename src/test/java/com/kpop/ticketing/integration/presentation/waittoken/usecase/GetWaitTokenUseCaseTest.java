package com.kpop.ticketing.integration.presentation.waittoken.usecase;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

	@Test
	@DisplayName("대기 토큰 조회 테스트")
	void getWaitTokenTest() {
		// given
		Long userId = 1L;

		WaitTokenResponse waitTokenResponse = getWaitTokenUseCase.execute(userId);

		// then
		assertThat(waitTokenResponse).isNotNull();
		assertThat(waitTokenResponse.getToken()).isEqualTo("token");
		assertThat(waitTokenResponse.getWaitingStatus()).isEqualTo(WaitingStatus.ONGOING);
		assertThat(waitTokenResponse.getWaitingNumber()).isEqualTo(0);
	}
}
