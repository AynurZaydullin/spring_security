package ru.skypro.lessons.springboot.spring_web_lessons.security;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String role;
}
