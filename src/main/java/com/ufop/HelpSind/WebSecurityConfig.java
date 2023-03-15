package com.ufop.HelpSind;

import javax.sql.DataSource;

import com.ufop.HelpSind.serviceImpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/images/**").permitAll()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/fonts/**").permitAll()
			.antMatchers("/vendors/**").permitAll()
			.anyRequest().authenticated();
		http.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/auth")
			.defaultSuccessUrl("/home")
			.permitAll()
			.and()
			.logout()
			.logoutUrl("/sair")
			.logoutSuccessUrl("/login")
			.deleteCookies("JSESSIONID");


	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

}
