package com.kpop.ticketing.domain.common.aspect;

import com.kpop.ticketing.domain.common.annotation.DistributedLock;
import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
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
        Long lockKey = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof Long) {
                lockKey = (Long) arg;
                break;
            }
        }

        if (lockKey == null) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        RLock rLock = redissonClient.getLock(String.format("lock:%s:%s", methodName, lockKey));
        DistributedLock lock = method.getAnnotation(DistributedLock.class);

        try {
            if (!rLock.tryLock(lock.waitTime(), lock.leaseTime(), lock.timeUnit())) {
                return false;
            }
            Object returnValue = joinPoint.proceed();
            rLock.unlock();
            return returnValue;
        } catch (final Exception e) {
            Thread.currentThread().interrupt();
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
