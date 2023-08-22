package com.udemy.springbootdemo.rest;

import com.udemy.springbootdemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    @Value("${coach.name}")
    private String coachName;

    @Value("${coach.team}")
    private String coachTeam;

    @GetMapping("/")
    public String sayHello() {
        return "Hello World! ";
    }

    @GetMapping("/team-info")
    public String teamInfo() {
        return "Hello World " + coachName + " in " + coachTeam;
    }
}
