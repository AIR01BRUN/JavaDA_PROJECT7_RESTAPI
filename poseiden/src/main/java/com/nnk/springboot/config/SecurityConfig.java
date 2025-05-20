package com.nnk.springboot.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.ProviderManager;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf() // Active la protection CSRF
                .and()
                .authorizeHttpRequests()
                // Définit les URLs publiques accessibles sans authentification
                .requestMatchers("/login", "/", "/css/**", "/js/**").permitAll()
                // Toutes les autres URLs nécessitent une authentification
                .anyRequest().authenticated()
                .and()
                .formLogin()
                // Configuration du formulaire de connexion
                .loginPage("/login")
                .failureHandler((request, response, exception) -> {
                    // Gestion des erreurs de connexion
                    request.getSession().setAttribute("error", "true");
                    response.sendRedirect("/login?error=true");
                })
                .defaultSuccessUrl("/bidList/list", true)
                .permitAll()
                .and()
                // Configuration de la déconnexion
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll();
        return http.build();
    }

    // Gestionnaire d'authentification personnalisé
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return new ProviderManager(Arrays.asList(authProvider()));
    }

    // Configuration du fournisseur d'authentification
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // Utilise notre service de détails utilisateur personnalisé
        authProvider.setUserDetailsService(userDetailsService());
        // Configure l'encodeur de mot de passe
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // Configuration du service de détails utilisateur avec JDBC
    @Bean
    public JdbcUserDetailsManager userDetailsService() {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        // Requête SQL pour récupérer l'utilisateur
        userDetailsManager.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM users WHERE username=? LIMIT 1");
        // Requête SQL pour récupérer les rôles
        userDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT username, role FROM users WHERE username=? LIMIT 1");
        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
