package com.zys.taimeiknowledge.aspect;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class aopAspect {


    @Pointcut("execution(public * com.zys.taimeiknowledge.controller..*.*(..))")
    public void log() {
        log.info("before cut1, do something");
    }

    @Around("log()")
    public Object Before(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        Object[] args = joinPoint.getArgs();
        String kind = joinPoint.getKind();
        String name = joinPoint.getSignature().getName();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String simpleName = joinPoint.getSignature().getDeclaringType().getSimpleName();
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        Object target = joinPoint.getTarget();
        Object aThis = joinPoint.getThis();
        System.out.println("around");
        return proceed;

    }

}
