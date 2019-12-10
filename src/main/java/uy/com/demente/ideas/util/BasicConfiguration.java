package uy.com.demente.ideas.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class BasicConfiguration extends WebSecurityConfigurerAdapter {

	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity//
				.authorizeRequests()//
				.anyRequest()//
				.authenticated()//
				.and()//
				.formLogin()//
				.and()//
				.httpBasic();
	}

}
