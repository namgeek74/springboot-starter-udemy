package com.udemy.springbootdemo.rest;

import com.udemy.springbootdemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // field injection (not recommended)
//    @Autowired
    private Coach myCoach;

    // constructor injection (recommended)
    @Autowired
    public DemoController(Coach theCoach) {
        myCoach = theCoach;
    }

// setter injection (recommended after constructor injection way)
//    @Autowired
//    public void setMyCoach(Coach theCoach) {
//        myCoach = theCoach;
//    }

    @GetMapping("/get-daily-workout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
