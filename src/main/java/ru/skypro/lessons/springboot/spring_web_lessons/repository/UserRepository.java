package ru.skypro.lessons.springboot.spring_web_lessons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.lessons.springboot.spring_web_lessons.service.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
