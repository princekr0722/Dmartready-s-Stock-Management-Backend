package com.dmart.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


//@Configuration
public class SecurityConfiguration {
	
//	@Autowired
	private AuthenticateApiRequest authenticateApiRequest;
	
	private static final String[] AUTH_WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/swagger-resources/**"
    };
	
//	@Bean
//	SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
//		
//		httpSecurity
//			.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//			.authorizeHttpRequests(httpRequest->{ httpRequest
//				.requestMatchers(AUTH_WHITE_LIST).permitAll()
//				.requestMatchers("/ws/**").permitAll()
//				.anyRequest().authenticated();
//			})
//			.addFilterBefore(authenticateApiRequest, BasicAuthenticationFilter.class)
//			.csrf(csrf -> csrf.disable())
//			.cors(cors -> cors.disable())
//			.formLogin(Customizer.withDefaults())
//			.httpBasic(Customizer.withDefaults());
//		
//		return httpSecurity.build();
//	}
//	
//	@Bean
//	PasswordEncoder getPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	CorsConfigurationSource getConfigurationSource() {
//		CorsConfiguration corsConfiguration = new CorsConfiguration();
//		corsConfiguration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500",
//				"http://localhost:8080/"
//				,"http://localhost:8081/"
//				,"http://localhost:8082/"
//				,"http://localhost:8083/"));
//		corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
//		corsConfiguration.setExposedHeaders(Arrays.asList("*"));
//		corsConfiguration.setAllowedMethods(Arrays.asList("*"));
//		corsConfiguration.setAllowCredentials(true);
//		
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//		source.registerCorsConfiguration("/**", corsConfiguration);
//		
//		return source;
//	}
}
