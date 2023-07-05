package ru.skypro.lessons.springboot.spring_web_lessons.dto;


public class ReportDTO {
    private String department;

    private int totalEmployees;

    private int maxSalary;

    private int minSalary;

    private int averageSalary;

    public ReportDTO(String department, int totalEmployees, int maxSalary, int minSalary, int averageSalary) {
        this.department = department;
        this.totalEmployees = totalEmployees;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        this.averageSalary = averageSalary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public int getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(int averageSalary) {
        this.averageSalary = averageSalary;
    }
}
