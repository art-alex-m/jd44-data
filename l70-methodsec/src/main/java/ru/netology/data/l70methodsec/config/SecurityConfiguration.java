package ru.netology.data.l70methodsec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.netology.data.l70methodsec.model.BasePermission;
import ru.netology.data.l70methodsec.model.BaseRole;

import java.util.List;

/**
 * Security configuration
 *
 * <p>
 * <a href="https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter/">Spring Security without the WebSecurityConfigurerAdapter</a><br>
 * <a href="https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/builders/HttpSecurity.html">Class HttpSecurity</a><br>
 * <a href="https://www.baeldung.com/spring-security-expressions">Intro to Spring Security Expressions</a><br>
 * </p>
 */
@Profile("security-enable")
@Configuration
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public UserDetailsService inMemoryUserDetailsManager() {
        UserDetails userRead = User.withUsername("alex")
                .password("{noop}0123456")
                .authorities(BasePermission.READ)
                .roles(BaseRole.USER)
                .build();
        UserDetails userSome = User.withUsername("some")
                .password("{noop}some")
                .authorities(BasePermission.WRITE, BasePermission.DELETE)
                .roles(BaseRole.DEVOPS)
                .build();
        UserDetails userSuper = User.withUsername("super")
                .password("{noop}super")
                .authorities(BasePermission.READ, BasePermission.WRITE)
                .roles(BaseRole.ADMIN)
                .build();

        return new InMemoryUserDetailsManager(List.of(userRead, userSuper, userSome));
    }
}
