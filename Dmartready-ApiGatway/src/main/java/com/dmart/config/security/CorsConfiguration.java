package com.dmart.config.security;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class CorsConfiguration implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Specify the allowed origins here, or use "*" to allow all origins
                .allowedMethods("*") // Specify the allowed HTTP methods here
                .allowedHeaders("*") // Specify the allowed headers here
                .allowCredentials(false) // Set to true if you want to allow credentials (e.g., cookies) to be sent with the request
                .maxAge(3600); // Specify the max age (in seconds) of the CORS preflight request
    }
}
