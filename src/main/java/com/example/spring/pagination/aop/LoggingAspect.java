package com.example.spring.pagination.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private final ObjectMapper objectMapper;

    @Pointcut("within(@com.example.spring.pagination.aop.annotation.Log *)")
    public void loggingPointCut() {
    }

    @SneakyThrows
    @Around(value = "loggingPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        var signature = ((MethodSignature) joinPoint.getSignature());
        var parameters = objectMapper.writeValueAsString(joinPoint.getArgs());
        logEvent("start", signature, parameters);
        Object response;

        try {
            response = joinPoint.proceed();
        } catch (Throwable throwable) {
            logEvent("error", signature, parameters);
            throw throwable;
        }
        logEndAction(signature, response);
        return response;
    }

    private void logEvent(String eventName, MethodSignature signature, String parameters) {
        log.info("ActionLog.{}.{} {}", signature.getName(), eventName, parameters);
    }

    private void logEndAction(MethodSignature signature, Object response) {
        if (void.class.equals(signature.getReturnType())) log.info("ActionLog.{}.end", signature.getName());
        else log.info("ActionLog.{}.end {}", signature.getName(), response);
    }

}
