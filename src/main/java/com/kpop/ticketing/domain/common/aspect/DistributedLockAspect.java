package com.kpop.ticketing.domain.common.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Aspect
@Order(value = 1)
public class DistributedLockAspect {

    private final RedissonClient redissonClient;

    @Around("@annotation(com.kpop.ticketing.domain.common.annotation.DistributedLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        // joinPoint는 호출된 메서드의 인자 정보를 가지고 있음
        Long seatId = (Long) joinPoint.getArgs()[1];
        RLock rLock = redissonClient.getLock(String.format("lock:%s", seatId));

        try {
            if (!rLock.tryLock()) {
                return false;
            }
            Object returnValue = joinPoint.proceed();
            rLock.unlock();
            return returnValue;
        } catch (final Exception e) {
            Thread.currentThread().interrupt();
            throw new InterruptedException();
        }
    }
}
