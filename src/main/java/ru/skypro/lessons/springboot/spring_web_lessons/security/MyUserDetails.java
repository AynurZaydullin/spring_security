//package ru.skypro.lessons.springboot.spring_web_lessons.security;
////    @Bean
////    public UserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) {
////
////        // Создаем пользователя Ivan с ролью USER
////        UserDetails ivan = User.withUsername("Ivan")
////                .password(passwordEncoder.encode("ivan1234"))
////                .roles("USER")
////                .build();
////
////        // Создаем пользователя Vladimir с ролью USER
////        UserDetails vladimir = User.withUsername("Vladimir")
////                .password(passwordEncoder.encode("vladimir1234"))
////                .roles("USER")
////                .build();
////
////        // Создаем пользователя admin с ролью ADMIN
////        UserDetails admin = User.withUsername("admin")
////                .password(passwordEncoder.encode("admin1234"))
////                .roles("USER","ADMIN")
////                .build();
////
////        // Возвращаем новый сервис управления InMemoryUserDetailsManager
////        // с добавленными пользователями (Ivan, Vladimir, admin)
////        return new InMemoryUserDetailsManager(ivan, vladimir, admin);
////    }
////
////    // Создаем бин кодировщика паролей (для хеширования паролей пользователей)
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import ru.skypro.lessons.springboot.spring_web_lessons.dto.UserDTO;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Optional;
//
//@Component
//public class MyUserDetails implements UserDetails {
//    private UserDTO userDTO;
//
//    public void setUserDTO(UserDTO userDTO) {
//        this.userDTO = userDTO;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Optional.ofNullable(userDTO)
//                .map(UserDTO::getRole)
//                .map(role -> new SimpleGrantedAuthority(role.name()))
//                .map(Collections::singleton)
//                .orElseGet(Collections::emptySet);
//    }
//
//    @Override
//    public String getPassword() {
//        return Optional.ofNullable(userDTO)
//                .map(UserDTO::getPassword)
//                .orElse(null);
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
