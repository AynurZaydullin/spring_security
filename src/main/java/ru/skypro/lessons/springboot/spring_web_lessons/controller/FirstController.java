package ru.skypro.lessons.springboot.spring_web_lessons.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.lessons.springboot.spring_web_lessons.service.CounterService;

@RestController
@RequestMapping("/FirstController")

public class FirstController {
    private final CounterService counterService;

    public FirstController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping("/Hello world!")
    public String showHelloWorld() {
        return "Hello world!";
    }

    @GetMapping("/count requests.")
    public Integer countRequests() {

        return counterService.showCounter();
    }
}
