package com.app.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.app.Service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
		
		http
			.authorizeRequests()
				.antMatchers(
						"/",
						"/about",
						"/register",
						"/registrationconfirmed",
						"/expiredtoken",
						"/invaliduser",
						"/confirmregistration", 
						"/registrationconfirmed", 
						"/expiredtoken", 
						"/verifyemail", 
						"/invaliduser",
						"/edit-profile-about")
				.permitAll()
				.antMatchers(
					"/js/*",
					"/css/*",
					"/img/*")
				.permitAll()
				.antMatchers(
						"/viewstatus",
						"/addstatus",
						"/deletestatus",
						"/editstatus")
				.hasRole("ADMIN")
				.antMatchers("/profile")
				.authenticated()
				.anyRequest()
				.denyAll()
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.failureUrl("/error.html")
//				.successHandler(successHandler())
				.permitAll()
				.and()
			.logout()
				.permitAll();
		
		// @formatter:on
	}
	
	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception {
	 * 
	 * // @formatter:off auth .inMemoryAuthentication() .withUser("john")
	 * .password("{noop}hello") .authorities("ROLE_USER") .and() .withUser("demo")
	 * .password("{noop}hello") .authorities("ROLE_USER","ROLE_ADMIN" );
	 * // @formatter:on
	 * 
	 * }
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

}