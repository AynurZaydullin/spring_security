package ru.skypro.lessons.springboot.spring_web_lessons.controller;


import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.lessons.springboot.spring_web_lessons.service.EmployeeService;
import ru.skypro.lessons.springboot.spring_web_lessons.service.Employee;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
//
//    @GetMapping
//    public Employee getEmployeeById(@RequestParam("Name") int name) {
//        return employeeService.getEmployeeById(name);
//    }

    @PostMapping(value = "/upload" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestPart("employees") MultipartFile employees) {
        try {
            employeeService.addEmployees(employees);
        } catch (Throwable t) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "/report/createReport")
    public long createReport() {
        try {
            return employeeService.createReport();
        } catch (Throwable t) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/report/{id}")
    public ResponseEntity<Resource> downloadReport(@PathVariable int id) {
        try {
            Resource resource = employeeService.downloadReport(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report.json\"")
                    .body(resource);
        } catch (Throwable t) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}