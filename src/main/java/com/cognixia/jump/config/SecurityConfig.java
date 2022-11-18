package com.cognixia.jump.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cognixia.jump.filter.JwtRequestFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
			}
		};
	}
	
	@Bean
	protected UserDetailsService userDetailsService() {
		return userDetailsService;
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/authenticate").permitAll()
			.antMatchers(HttpMethod.POST, "/api/user").permitAll()
			.antMatchers(HttpMethod.GET, "/api/user").hasRole("ADMIN")
			.antMatchers(HttpMethod.PUT, "/api/user").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/api/order").hasAnyRole("ADMIN","USER")
			.antMatchers(HttpMethod.POST, "/api/order").hasAnyRole("ADMIN","USER")
			.antMatchers(HttpMethod.DELETE, "/api/order").hasAnyRole("ADMIN","USER")
			.antMatchers(HttpMethod.DELETE, "/api/clothes").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/api/clothes").hasRole("ADMIN")
			.antMatchers(HttpMethod.PUT, "/api/clothes").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/api/clothes").permitAll()
			.antMatchers(HttpMethod.GET, "/api/clothes/{id}").permitAll()
			.antMatchers(HttpMethod.GET, "/api/clothes/age/{age}").permitAll()
			.antMatchers(HttpMethod.GET, "/api/clothes/gender/{gender}").permitAll()
			.antMatchers(HttpMethod.GET, "/api/clothes/type/{type}").permitAll()
			.antMatchers(HttpMethod.GET, "/api/clothes/color/{color}").permitAll()
			.antMatchers(HttpMethod.GET, "/swagger-ui/index.html").permitAll()
			.antMatchers(HttpMethod.GET, "/v3/api-docs").hasRole("ADMIN")
//			.anyRequest().authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS );
		
		http.addFilterBefore( jwtRequestFilter, UsernamePasswordAuthenticationFilter.class );
		
		return http.build();
	}
	
	@Bean
	protected PasswordEncoder encoder() {
//		return NoOpPasswordEncoder.getInstance();
				
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder( encoder() );
		
		return authProvider;
	}
	
	@Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
}
