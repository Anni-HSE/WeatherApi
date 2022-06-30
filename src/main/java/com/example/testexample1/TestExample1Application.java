package com.example.testexample1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com"})
public class TestExample1Application {
    public static void main(String[] args) {
        SpringApplication.run(TestExample1Application.class, args);
    }
}
