package ru.skypro.lessons.springboot.spring_web_lessons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.spring_web_lessons.dto.ReportDTO;
import ru.skypro.lessons.springboot.spring_web_lessons.service.pojo.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//    List<Employee> getAllEmployees();

    @Query("SELECT new ru.skypro.lessons.springboot.spring_web_lessons.dto.ReportDTO(e.department.name, count (e.id), max(e.salary), min(e.salary), avg(e.salary)) FROM Employee e GROUP BY e.department.name")
    List<ReportDTO> buildReports();
}
