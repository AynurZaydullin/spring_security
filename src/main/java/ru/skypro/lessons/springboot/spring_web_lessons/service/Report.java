package ru.skypro.lessons.springboot.spring_web_lessons.service;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.Instant;

@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Lob
    @Column(columnDefinition = "oid")
    private String report;

    @CreationTimestamp(source = SourceType.DB)
    @Column(updatable = false)
    private Instant createAt;

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }
    //    @Column(name = "department")
//    private String department;
//
//    @Column(name = "totalEmployees")
//    private int totalEmployees;
//
//    @Column(name = "maxSalary")
//    private int maxSalary;
//
//    @Column(name = "minSalary")
//    private int minSalary;
//
//    @Column(name = "averageSalary")
//    private int averageSalary;

//    public String getReport() {
//        return report;
//    }
//
//    public void setReport(String report) {
//        this.report = report;
//    }


//    public String getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }
//
//    public int getTotalEmployees() {
//        return totalEmployees;
//    }
//
//    public void setTotalEmployees(int totalEmployees) {
//        this.totalEmployees = totalEmployees;
//    }
//
//    public int getMaxSalary() {
//        return maxSalary;
//    }
//
//    public void setMaxSalary(int maxSalary) {
//        this.maxSalary = maxSalary;
//    }
//
//    public int getMinSalary() {
//        return minSalary;
//    }
//
//    public void setMinSalary(int minSalary) {
//        this.minSalary = minSalary;
//    }
//
//    public int getAverageSalary() {
//        return averageSalary;
//    }
//
//    public void setAverageSalary(int averageSalary) {
//        this.averageSalary = averageSalary;
//    }
}