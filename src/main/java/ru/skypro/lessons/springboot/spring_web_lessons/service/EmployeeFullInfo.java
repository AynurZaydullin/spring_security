package ru.skypro.lessons.springboot.spring_web_lessons.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeFullInfo {
    // Имя сотрудника
    private String name;
    // Зарплата сотрудника
    private Integer salary;
    // Название должности сотрудника
    private String positionName;


    // Конструктор класса EmployeeFullInfo
//    public EmployeeFullInfo(String name, Integer salary, String positionName) {
//        this.name = name;
//        this.salary = salary;
//        this.positionName = positionName;
//    }

    // Геттеры и сеттеры
}