package com.nnk.springboot.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for Spring Security.
 * Handles authentication, authorization, and password encoding.
 */
@Configuration
public class SecurityConfig {

    private final DataSource dataSource;

    /**
     * Constructor that injects the DataSource for database authentication
     * 
     * @param dataSource the data source for user authentication
     */
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Configures the security filter chain with authentication and authorization
     * rules
     * 
     * @param http the HttpSecurity to configure
     * @return the built SecurityFilterChain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/login", "/").permitAll()
                // .requestMatchers("/user/**").hasRole("ADMIN") // Recupere le role admin mais
                // affiche un acced denied aux deux roles
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/bidList/list")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .permitAll();

        return http.build();
    }

    /**
     * Configures the JDBC-based user details service
     * 
     * @return configured JdbcUserDetailsManager
     */
    @Bean
    public JdbcUserDetailsManager userDetailsService() {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        // SQL query to retrieve user details
        userDetailsManager.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM users WHERE username=?");
        // SQL query to retrieve user roles
        userDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT username, role FROM users WHERE username=?");
        return userDetailsManager;
    }

    /**
     * Configures the password encoder for secure password storage
     * 
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
