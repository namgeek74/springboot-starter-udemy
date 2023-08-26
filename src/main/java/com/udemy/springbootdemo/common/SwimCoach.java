package com.udemy.springbootdemo.common;

public class SwimCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Swim coach";
    }
}
