package com.tr.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguaration {
	
//	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		/*All requests should be authenticated
		 * If request is not authenticated it will through web page
		 * Enables CSRF->would impact on post and put
		*/
		//All requests should be authenticated
		http.authorizeHttpRequests(
				auth->auth.anyRequest().authenticated());
		//If request is not authenticated it will through web page
		http.httpBasic(withDefaults());
		
		//Enables CSRF->would impact on post and put
//		http.csrf().disable();
		http.csrf(auth->auth.disable());
		return http.build();
	}
	
}
