package org.ejatohvee.taskmanagementsystem.configs;

import lombok.RequiredArgsConstructor;
import org.ejatohvee.taskmanagementsystem.security.JwtRequestFilter;
import org.ejatohvee.taskmanagementsystem.security.PasswordEncoderConfig;
import org.ejatohvee.taskmanagementsystem.security.UserDetailsManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsManagerImpl userDetailsManager;
    private final JwtRequestFilter jwtRequestFilter;
    private final PasswordEncoderConfig passwordEncoderConfig;

    @Bean
    public AuthenticationManager authenticationManager() {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsManager);
        authProvider.setPasswordEncoder(passwordEncoderConfig.passwordEncoder());
        return new ProviderManager(authProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                        .requestMatchers("/api/auth/login", "/api/auth/register").permitAll()
                        .requestMatchers("/api-docs/**", "/swagger-ui/**", "/swagger-ui/index.html").permitAll()
                        .anyRequest().authenticated()
                        )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
