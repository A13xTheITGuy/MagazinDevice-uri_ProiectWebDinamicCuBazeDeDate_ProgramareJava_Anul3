package com.alex.magazin_deviceuri.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.alex.magazin_deviceuri.services.UtilizatorLoginService;

@Configuration
public class SpringSecurityConfiguration {
	
	@Autowired
	private UtilizatorLoginService utilizatorLoginService;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/login", "/register", "/changePassword", "/css/**", "/js/**").permitAll()
            .requestMatchers("/lista-deviceuri").hasAnyRole("USER", "EDITOR", "ADMIN")
            .requestMatchers("/produse/**").hasAnyRole("EDITOR", "ADMIN")
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .usernameParameter("username")
            .passwordParameter("parola")
            .defaultSuccessUrl("/lista-deviceuri", true)
            .failureUrl("/login?error=true")
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout=true")
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .deleteCookies("JSESSIONID")
            .permitAll()
        );
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}