package ru.skypro.lessons.springboot.spring_web_lessons.service.pojo;

import jakarta.persistence.*;
import ru.skypro.lessons.springboot.spring_web_lessons.service.Position;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String department;

    private String name;
    private int salary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private Position position;
    public Employee() {
    }

    public Employee(Long id, String department, String name, int salary) {
        this.id = id;
        this.department = department;
        this.name = name;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
