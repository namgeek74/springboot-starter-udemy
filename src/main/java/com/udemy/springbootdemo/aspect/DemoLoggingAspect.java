package com.udemy.springbootdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class DemoLoggingAspect {
    // declare a pointcut


    @Pointcut("execution(* com.udemy.springbootdemo.dao.*.*(..))")
    private void forDAOPackage() {
    }

    @Pointcut("execution(* com.udemy.springbootdemo.dao.*.set*(..))")
    private void setter() {
    }

    @Pointcut("execution(* com.udemy.springbootdemo.dao.*.get*(..))")
    private void getter() {
    }

    @Pointcut("forDAOPackage() && !(setter() || getter())")
    private void forDAOPackageNoGetterSetter() {
    }

    // this is where we add all of our related advices for logging

    // let's start with a @Before advice
    @Before("com.udemy.springbootdemo.aspect.PointCutExpression.forAddAccountMethod()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("=====>>> Executing @Before advice on addAccount()");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("method: " + methodSignature);

        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            System.out.println(arg);
        }

    }

    @AfterReturning(
            pointcut = "com.udemy.springbootdemo.aspect.PointCutExpression.testAfterReturningPointCut()",
            returning = "result"
    )
    public void afterReturningAdvice(JoinPoint joinPoint, StringBuilder result) {
        System.out.println("=====>>> @AfterReturning advice");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("method: " + methodSignature);

        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            System.out.println(arg);
        }
        System.out.println("result in advice: " + result);

        result.append(" extra data in after advice");
    }

    @AfterThrowing(
            pointcut = "execution(* throwDemoa(..))",
            throwing = "exc"
    )
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception exc) {
        System.out.println("=====>>> @AfterThrowing advice");
        System.out.println("method: " + joinPoint.getSignature().toShortString());
        System.out.println("exception in advice: " + exc);
    }

    @After("execution(* throwDemoa(..)) || execution(* testAfterReturning(..))")
    public void afterAdvice() {
        System.out.println("=====>>> @After advice");
    }

    @Around("execution(* throwDemo(..))")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("@Around advice");
        System.out.println("method: " + proceedingJoinPoint.getSignature().toShortString());
        Object result = null;
        long begin = System.currentTimeMillis();
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            System.out.println("Some error in @Around advice");
            result = "Nothing here, move along!";
        }
        long end = System.currentTimeMillis();

        long duration = end - begin;
        System.out.println("Duration: " + duration / 1000.0 + " seconds");

        return result;

    }
}
