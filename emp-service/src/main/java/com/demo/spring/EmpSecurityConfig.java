package com.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
@EnableWebSecurity
public class EmpSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	javax.sql.DataSource datasource;
@Override
protected void configure(HttpSecurity http) throws Exception {
	// TODO Auto-generated method stub
	http.authorizeRequests().antMatchers("/index").permitAll();
	http
	.authorizeRequests()
	.antMatchers("/index")
	.permitAll()
	.antMatchers("/register")
	.hasRole("USER")
	.and()
	.httpBasic()
	.and()
	.csrf().disable();
}
/*@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
auth.inMemoryAuthentication().withUser("pavan").password(passwordEncoder().encode("welcome1")).roles("USER");
auth.inMemoryAuthentication().withUser("pooja").password(passwordEncoder().encode("welcome1")).roles("USER");
auth.inMemoryAuthentication().withUser("john").password(passwordEncoder().encode("welcome1")).roles("USER");
auth.inMemoryAuthentication().withUser("pavani").password(passwordEncoder().encode("welcome1")).roles("USER").disabled(true);
}*/
@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
	auth.jdbcAuthentication().dataSource(datasource);
}
@Bean
public BCryptPasswordEncoder passwordEncoder()
{
	return new BCryptPasswordEncoder();
}
}
