package ru.skypro.lessons.springboot.spring_web_lessons.security;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.spring_web_lessons.repository.UserRepository;
import ru.skypro.lessons.springboot.spring_web_lessons.security.AuthUser;
import ru.skypro.lessons.springboot.spring_web_lessons.security.SecurityUserPrincipal;

@Service
@AllArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService{

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(username);
        }
        return new SecurityUserPrincipal(user);
    }
}
