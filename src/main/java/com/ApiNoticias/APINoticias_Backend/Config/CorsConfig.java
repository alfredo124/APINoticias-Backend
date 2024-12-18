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
        corsConfiguration.addAllowedOrigin("*"); // Cambia por tu dominio de Vercel

        // Permitir métodos específicos
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addAllowedMethod("PUT");
        corsConfiguration.addAllowedMethod("DELETE");
        corsConfiguration.addAllowedMethod("OPTIONS");
        // Permitir encabezados específicos
        corsConfiguration.addAllowedHeader("*"); // Permitir todos los encabezados

        // Si necesitas permitir cookies o credenciales
        corsConfiguration.setAllowCredentials(false);

        // Configura CORS para todas las rutas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}
