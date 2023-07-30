package com.fastcampus.ch3.di3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean
    Car car() {return new Car();}

    @Bean
    @Scope("prototype")
    Engine engine() {return new Engine();}

    @Bean
    Door door() {return new Door();}
}
