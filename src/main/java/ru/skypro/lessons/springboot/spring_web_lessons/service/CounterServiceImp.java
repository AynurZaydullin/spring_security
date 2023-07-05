package ru.skypro.lessons.springboot.spring_web_lessons.service;

import org.springframework.stereotype.Service;

@Service
public class CounterServiceImp implements CounterService{
    private Integer count = 0;
    @Override
    public Integer showCounter() {
        return ++count;
    }
}
