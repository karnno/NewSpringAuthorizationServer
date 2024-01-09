package com.appsdeveloperblog.photoapp.oauthserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfiguration {

	@Bean
	SecurityFilterChain configureSecurityFilterChain(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests(
					authorizeRequests -> authorizeRequests.anyRequest().authenticated()
					)
			.formLogin(Customizer.withDefaults());
		
		return http.build();
		
	}
	
	/*
	 * Next, I'll create a method to configure user credentials that can be used to
	 * authenticate using this login form.
	 * 
	 * Notice that this method is also annotated with a bin annotation and it should
	 * return an object of user
	 * 
	 * detail service that is part of spring security package.
	 */
	@Bean
	public UserDetailsService users() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		UserDetails user  = User.withUsername("sergey").password(encoder.encode("password")).roles("SUER").build();
		
		return new InMemoryUserDetailsManager(user);
	}
}
