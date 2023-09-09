package com.udemy.springbootdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoLoggingAspect {
    // declare a pointcut
    @Pointcut("execution(public void addAccount())")
    private void forAddAccountMethod() {
    }

    @Pointcut("execution(* com.udemy.springbootdemo.dao.*.*(..))")
    private void forDAOPackage() {
    }

    @Pointcut("execution(* com.udemy.springbootdemo.dao.*.set*(..))")
    private void setter() {
    }

    @Pointcut("execution(* com.udemy.springbootdemo.dao.*.get*(..))")
    private void getter() {
    }

    @Pointcut("forDAOPackage() && !(setter() || getter()")
    private void forDAOPackageNoGetterSetter() {
    }

    // this is where we add all of our related advices for logging

    // let's start with a @Before advice
    @Before("forAddAccountMethod()")
    public void beforeAddAccountAdvice() {
        System.out.println("=====>>> Executing @Before advice on addAccount()");
    }
}
