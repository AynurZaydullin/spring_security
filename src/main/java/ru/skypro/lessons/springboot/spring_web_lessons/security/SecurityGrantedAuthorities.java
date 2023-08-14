package ru.skypro.lessons.springboot.spring_web_lessons.security;

import org.springframework.security.core.GrantedAuthority;

public class SecurityGrantedAuthorities implements GrantedAuthority {

    private String role;

    public SecurityGrantedAuthorities(Authority authority) {
        this.role = authority.getRole();
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
