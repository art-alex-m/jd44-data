package ru.netology.data.l41daohibernate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    public static final String AUTHORITY_READ = "read";
    public static final String AUTHORITY_CUSTOM = "custom";

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails userRead = User.withUsername("alex")
                .password("{noop}0123456")
                .authorities(AUTHORITY_READ)
                .build();
        UserDetails userSuper = User.withUsername("super")
                .password("{noop}super")
                .authorities(AUTHORITY_READ, AUTHORITY_CUSTOM)
                .build();

        return new InMemoryUserDetailsManager(List.of(userRead, userSuper));
    }


    @Bean
    @Order(10)
    public SecurityFilterChain securityApiFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .securityMatchers(m -> m.requestMatchers("/person/**", "/persons/**"))
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(a -> a.requestMatchers("/person/**").hasAuthority(AUTHORITY_READ))
                .authorizeHttpRequests(a -> a.requestMatchers("/persons/**").hasAuthority(AUTHORITY_CUSTOM))
                .authorizeHttpRequests(a -> a.anyRequest().denyAll());

        return httpSecurity.build();
    }

    @Bean
    @Order(1000)
    public SecurityFilterChain securityDefaultFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .logout(LogoutConfigurer::permitAll)
                .authorizeHttpRequests(a -> a.anyRequest().denyAll());

        return httpSecurity.build();
    }
}
