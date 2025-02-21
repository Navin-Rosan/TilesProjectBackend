package com.TilesDesign.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;


@Configuration
public class TilesConfig {
	private final PasswordEncoder passwordEncoder;
	
	public TilesConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	
        http.csrf(csrf -> csrf.disable())
		        .cors(cors -> cors.configurationSource(request -> {
		            CorsConfiguration config = new CorsConfiguration();
		            config.addAllowedOrigin("*"); 
		            config.addAllowedMethod("*");
		            config.addAllowedHeader("*");
		            config.setAllowCredentials(true);
		            return config;
		        }))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/design/register", "/design/login", "/design/public/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(login -> login.disable());
		
		return http.build();
	}
}
