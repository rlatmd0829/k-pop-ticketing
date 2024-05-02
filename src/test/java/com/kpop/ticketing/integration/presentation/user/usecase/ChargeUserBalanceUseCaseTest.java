package com.kpop.ticketing.integration.presentation.user.usecase;

import com.kpop.ticketing.domain.user.infrastructure.UserJpaRepository;
import com.kpop.ticketing.presentation.user.dto.request.UserBalanceRequest;
import com.kpop.ticketing.presentation.user.usecase.ChargeUserBalanceUseCase;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@Sql(scripts = {
    "classpath:data/users.sql"
})
class ChargeUserBalanceUseCaseTest {

    @Autowired
    private ChargeUserBalanceUseCase chargeUserBalanceUseCase;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @AfterEach
    void tearDown() {
        userJpaRepository.deleteAll();
    }

    @Test
    void chargeUserBalanceTest() throws InterruptedException {
        // given
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);

        UserBalanceRequest userBalanceRequest = UserBalanceRequest.create(10000);

        // when
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(() -> {
                try {
                    chargeUserBalanceUseCase.execute(1L, userBalanceRequest);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        executorService.shutdown();

        // then
        assertThat(userJpaRepository.findById(1L).get().getBalance()).isEqualTo(110000);

    }
}
