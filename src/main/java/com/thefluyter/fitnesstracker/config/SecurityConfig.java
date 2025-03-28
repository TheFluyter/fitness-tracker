package com.thefluyter.fitnesstracker.config;

import com.thefluyter.fitnesstracker.service.fitnessuserdetails.FitnessUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final FitnessUserDetailsService userDetailsService;

    @Bean
    UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());
        http.authorizeHttpRequests(auth ->
                auth.anyRequest().permitAll()
            )
            .csrf().disable()
            .formLogin(login ->
                login
                    .loginPage("/login")
                    .usernameParameter("email")
                    .defaultSuccessUrl("/")
                    .permitAll()
            )
            .logout(logout -> logout.logoutSuccessUrl("/").permitAll()
            );

        return http.build();
    }

}
