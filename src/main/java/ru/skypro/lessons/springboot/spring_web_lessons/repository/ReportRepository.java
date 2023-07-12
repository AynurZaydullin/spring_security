package ru.skypro.lessons.springboot.spring_web_lessons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.lessons.springboot.spring_web_lessons.service.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {
}