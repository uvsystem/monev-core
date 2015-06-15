package com.unitedvision.sangihe.monev.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.unitedvision.sangihe.monev.entity.Operator.Role;
import com.unitedvision.sangihe.monev.security.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
@ComponentScan("com.unitedvision.sangihe.monev.security")
@Import(ApplicationConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomAuthenticationProvider authenticationProvider;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
    	web.ignoring().antMatchers("/resources/**");
		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
        .authorizeRequests()
        	.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        	.antMatchers(HttpMethod.GET, "/skpd/**").permitAll()
        	.antMatchers(HttpMethod.GET, "/kegiatan/**").permitAll()
			.antMatchers("/operator/login/**").permitAll()
			.antMatchers("/operator").hasRole(Role.ADMIN.name())
			.anyRequest().authenticated()
            .and()
        .httpBasic();
	}
}
