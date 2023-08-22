package com.udemy.springbootdemo.common;

import com.udemy.springbootdemo.common.Coach;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowl in 15 minutes";
    }
}
