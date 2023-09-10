package com.udemy.springbootdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO {
    @Override
    public void addAccount() {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    @Override
    public StringBuilder testAfterReturning(int num) {
        return new StringBuilder("test after returning advice " + num);
    }

    @Override
    public void throwDemo() {
        throw new RuntimeException("new exception in runtime");
        // double value = 10 / 0;
    }
}
