package ru.skypro.lessons.springboot.spring_web_lessons;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
//@SuppressWarnings("removal")
@AllArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    // Создаем бин для хранения пользователей в памяти приложения
//    @Bean
//    public UserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) {
//
//        // Создаем пользователя Ivan с ролью USER
//        UserDetails ivan = User.withUsername("Ivan")
//                .password(passwordEncoder.encode("ivan1234"))
//                .roles("USER")
//                .build();
//
//        // Создаем пользователя Vladimir с ролью USER
//        UserDetails vladimir = User.withUsername("Vladimir")
//                .password(passwordEncoder.encode("vladimir1234"))
//                .roles("USER")
//                .build();
//
//        // Создаем пользователя admin с ролью ADMIN
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder.encode("admin1234"))
//                .roles("USER","ADMIN")
//                .build();
//
//        // Возвращаем новый сервис управления InMemoryUserDetailsManager
//        // с добавленными пользователями (Ivan, Vladimir, admin)
//        return new InMemoryUserDetailsManager(ivan, vladimir, admin);
//    }
//
//    // Создаем бин кодировщика паролей (для хеширования паролей пользователей)
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource,
//                                                 AuthenticationManager authenticationManager) {
//
//        // Инициализируем JdbcUserDetailsManager с dataSource
//        // и authenticationManager для работы с базой данных
//        JdbcUserDetailsManager jdbcUserDetailsManager =
//                new JdbcUserDetailsManager(dataSource);
//
//        jdbcUserDetailsManager.setAuthenticationManager(authenticationManager);
//        return jdbcUserDetailsManager;
//    }
//
//    // Создаем бин authenticationManager и получаем его
//    // из AuthenticationConfiguration.
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//            throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//// Создаем класс SecurityConfig, который конфигурирует
//// настройки безопасности нашего приложения.
//



    @Autowired
    // Внедряем зависимость UserDetailsService
    // для работы с данными пользователя.
    private UserDetailsService userDetailsService;

    @Bean
    // Создаем экземпляр PasswordEncoder для шифрования паролей.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    // Создаем экземпляр DaoAuthenticationProvider
    // для работы с аутентификацией через базу данных.
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        // Устанавливаем наш созданный экземпляр PasswordEncoder
        // для возможности использовать его при аутентификации.
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests(this::customizeRequest);
        // Цепочка фильтров безопасности для обработки входящих запросов,
        // основанная на настройках безопасности,
        // определенных с помощью метода 'customizeRequest'.

        return http.build();
    }

    private void customizeRequest(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        try {
            registry.requestMatchers(new AntPathRequestMatcher("/admin/**"))
                    .hasAnyRole("ADMIN")
                    // Определяем правило авторизации для запросов
                    // к URL, которые начинаются с "/admin/",
                    // позволяя доступ только пользователям с ролью "ADMIN".

                    .requestMatchers(new AntPathRequestMatcher("/**"))
                    .hasAnyRole("USER")
                    // Определяем правило авторизации для остальных запросов,
                    // позволяя доступ только пользователям с ролью "USER".

                    .and()
                    .formLogin().permitAll()
                    // Позволяем всем пользователям доступ к форме входа.

                    .and()
                    .logout().logoutUrl("/logout");
            // Настраиваем механизм выхода из системы
            // с заданием URL "/logout".

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}