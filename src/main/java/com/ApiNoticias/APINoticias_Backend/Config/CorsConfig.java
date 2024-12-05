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

        // Permitir solicitudes de este origen (Vercel)
        corsConfiguration.addAllowedOrigin("https://api-noticias1-1914.vercel.app"); // Cambia esto por tu dominio de Vercel

        // Permitir ciertos métodos
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addAllowedMethod("PUT");
        corsConfiguration.addAllowedMethod("DELETE");

        // Permitir encabezados específicos
        corsConfiguration.addAllowedHeader("*");

        // Si es necesario permitir cookies o credenciales
        corsConfiguration.setAllowCredentials(true);

        // Aplicar la configuración a todas las rutas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}

