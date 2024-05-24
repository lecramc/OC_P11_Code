package com.example.Hospital.Auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration  // Indique que cette classe est une configuration Spring
@EnableWebSecurity  // Active la sécurité web de Spring
public class SecurityConfig {

    @Bean  // Définit un bean pour la chaîne de filtres de sécurité
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Désactive la protection CSRF
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry
                        -> authorizationManagerRequestMatcherRegistry.requestMatchers("/**").authenticated()) // Nécessite une authentification pour toutes les requêtes
                .httpBasic(Customizer.withDefaults()) // Utilise l'authentification de base HTTP
                .sessionManagement(httpSecuritySessionManagementConfigurer
                        -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Utilise des sessions sans état
                .addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);  // Ajoute un filtre d'authentification personnalisé
        return http.build();  // Construit et retourne l'objet SecurityFilterChain
    }

}
