package com.mavin.SapConnectService.config;

import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

	@Bean
	@Override
	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) {
	  KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
	  auth.authenticationProvider(keycloakAuthenticationProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  super.configure(http);
	  
//	  http = http.cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable());
	  http  = http.cors(cors -> cors.disable());
	  http = http.csrf(csrf -> csrf.disable() );
	  
	  http.authorizeRequests(requests -> requests
		  .antMatchers("/api/auth/**").permitAll()
		  .anyRequest().authenticated());
    
//	  http
//	  .authorizeRequests()
//	  .anyRequest()
//	  .authenticated();
	}
	
}
