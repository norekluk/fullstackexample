package org.example.springbeexample.controller;

import org.example.springbeexample.service.SpringBeExampleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBeExampleController {

    private final SpringBeExampleService service;

    public SpringBeExampleController(final SpringBeExampleService service){
        this.service = service;
    }

    @RequestMapping
    public String helloWorld() {
        return "Hello World from Spring Boot";
    }

    @RequestMapping("/goodbye")
    public String goodbye() {
        return "Goodbye from SpringBoot";
    }
}
