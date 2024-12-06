package com.ApiNoticias.APINoticias_Backend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // Permitir solicitudes desde tu dominio en Vercel
        corsConfiguration.addAllowedOrigin("https://api-noticias1-1914.vercel.app");

        // Permitir métodos
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addAllowedMethod("PUT");
        corsConfiguration.addAllowedMethod("DELETE");

        // Permitir cabeceras
        corsConfiguration.addAllowedHeader("*");

        // Si se necesitan credenciales (cookies, cabeceras de autenticación)
        corsConfiguration.setAllowCredentials(true);

        // Configura el manejo de solicitudes OPTIONS
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}
