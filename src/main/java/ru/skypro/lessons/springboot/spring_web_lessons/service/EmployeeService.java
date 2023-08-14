package ru.skypro.lessons.springboot.spring_web_lessons.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.spring_web_lessons.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.spring_web_lessons.exception.IllegalJsonFileException;
import ru.skypro.lessons.springboot.spring_web_lessons.exception.InternalServerError;
import ru.skypro.lessons.springboot.spring_web_lessons.exception.ReportNotFoundException;
import ru.skypro.lessons.springboot.spring_web_lessons.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.spring_web_lessons.repository.ReportRepository;
import ru.skypro.lessons.springboot.spring_web_lessons.dto.ReportDTO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ReportRepository reportRepository;
    private final ObjectMapper objectMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ReportRepository reportRepository, ObjectMapper objectMapper) {
        this.employeeRepository = employeeRepository;
        this.reportRepository = reportRepository;
        this.objectMapper = objectMapper;
    }

    public void createBatchEmployees(List<EmployeeDTO> employees) {
        employees.stream()
                .map(EmployeeDTO::toEmployee)
                .forEach(employeeRepository::save);
    }
    public String buildReport() throws JsonProcessingException {
        List<ReportDTO> reports = employeeRepository.buildReports();
        return objectMapper.writeValueAsString(reports);
    }
    public void addEmployees(MultipartFile employees) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String extension = StringUtils.getFilenameExtension(employees.getOriginalFilename());
            if (!"json".equals(extension)) {
                throw new IllegalJsonFileException();
            }
            List<EmployeeDTO> employeeDTOS = objectMapper.readValue(
                    employees.getBytes(),
                    new TypeReference<>() {

                    }
            );
            createBatchEmployees(employeeDTOS);
        }catch (IOException e) {
            e.printStackTrace();
            throw new IllegalJsonFileException();
        }
    }

    public Long createReport() {
        try {
            Report report = new Report();
            report.setReport(buildReport());
            return reportRepository.save(report).getId();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new InternalServerError();
        }
    }

    public Resource downloadReport(int id) {
        return new ByteArrayResource(
                reportRepository.findById(id)
                        .orElseThrow(ReportNotFoundException::new)
                        .getReport()
                        .getBytes(StandardCharsets.UTF_8)
        );
    }
//    private final EmployeeMapper employeeMapper;
//    private final ObjectMapper objectMapper;
//    private final ReportRepository reportRepository;
//
//    public EmployeesService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper,   ObjectMapper objectMapper, ReportRepository reportRepository) {
//        this.employeeRepository = employeeRepository;
//        this.employeeMapper = employeeMapper;
//        this.objectMapper = objectMapper;
//        this.reportRepository = reportRepository;
//    }
//    // Задаем имя файла для сохранения
////        String fileName = "file.txt";
////        String filePath = "employees.json";
//
//    public void saveFileIntoBD(MultipartFile employees) {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            String extension = StringUtils.getFilenameExtension(employees.getOriginalFilename());
//            if (!"json".equals(extension)) {
//                throw new IllegalJsonFileException();
//            }
////            List<EmployeeDTO> employeeDTOS = objectMapper.readValue(
////                    employees.getBytes(),
////                    new TypeReference<>() {
////
////                    }
////            );
////            createBatchEmployees(employeeDTOS);
//        }catch (IOException e) {
//            e.printStackTrace();
//            throw new IllegalJsonFileException();
//        }
//    }
//
//    public void report() {
//        Report report = new Report();
//        report.setReport();
//        reportRepository.save(report);
//    }
//



//    @Override
//    public Employee getEmployeeById(int id) {
//        // Используем метод findById() репозитория для получения сотрудника по id
//        // Возвращает Optional<Employee>,
//        // который может содержать сотрудника или быть пустым
//        Optional<Employee> employeeOptional = employeeRepository.findById(id);
//
//        // Если сотрудник найден, возвращаем его
//        // Иначе выбрасываем исключение с указанием некорректного id
//        return employeeOptional.orElseThrow(() -> new IncorrectEmployeeIdException(id));
//    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }
//
//    @Override
//    public Employee getEmployeeById(int name) {
//        return employeeRepository.getAllEmployees().get(name);
//    }
}