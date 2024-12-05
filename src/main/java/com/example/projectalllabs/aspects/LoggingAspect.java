package com.example.projectalllabs.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.example.projectalllabs.services.*.*(..))")
    public void logBefore() {
        System.out.println("Before method execution");
    }

    @After("execution(* com.example.projectalllabs.services.*.*(..))")
    public void logAfter() {
        System.out.println("After method execution");
    }

    @AfterReturning("execution(* com.example.projectalllabs.services.*.*(..))")
    public void logAfterReturning() {
        System.out.println("After method successfully returned");
    }

    @AfterThrowing("execution(* com.example.projectalllabs.services.*.*(..))")
    public void logAfterThrowing() {
        System.out.println("After method threw an exception");
    }

    @Around("execution(* com.example.projectalllabs.services.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around method execution started");
        Object result = joinPoint.proceed();
        System.out.println("Around method execution completed");
        return result;
    }
}
