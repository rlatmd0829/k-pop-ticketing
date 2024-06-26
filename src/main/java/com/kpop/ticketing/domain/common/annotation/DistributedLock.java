package com.kpop.ticketing.domain.common.annotation;

import com.kpop.ticketing.domain.common.aspect.LockType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {
    LockType lockType() default LockType.DEFAULT;

    TimeUnit timeUnit() default TimeUnit.SECONDS;

    long waitTime() default 10L;

    long leaseTime() default 1L;
}
