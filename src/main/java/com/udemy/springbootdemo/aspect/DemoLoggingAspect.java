package com.udemy.springbootdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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
}
