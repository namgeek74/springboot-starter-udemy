package com.udemy.springbootdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCutExpression {
    @Pointcut("execution(public void addAccount())")
    public void forAddAccountMethod() {
    }

    @Pointcut("execution(* testAfterReturning(..))")
    public void testAfterReturningPointCut() {
    }
}
