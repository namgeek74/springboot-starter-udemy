package com.udemy.springbootdemo.config;

import com.udemy.springbootdemo.common.Coach;
import com.udemy.springbootdemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    @Bean("customIdForSwimCoach")
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
