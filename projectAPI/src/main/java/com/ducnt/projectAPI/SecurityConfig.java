//package com.ducnt.projectAPI;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@SuppressWarnings("deprecation")
//@Configuration
//@EnableWebSecutiry
//@EnableGlobalMethodSecurity(securedEnabled = true,
//	prePostEnabled = true, jsr250Enabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	UserDetailsService userDetailsService;
//
//	//xac thuc
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//	}
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//				.antMatchers("/category/**")
//				// .hasAnyRole("ADMIN","SUBADMIN")//ROLE_
//				.hasAnyAuthority("ROLE_ADMIN")
//				.antMatchers("/bill/**").authenticated()
//				.antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
//				.anyRequest().permitAll().and()
//				.csrf().disable()
//
//				.formLogin();
////				.loginPage("/dang-nhap")
////				.loginProcessingUrl("/login")
////				.failureUrl("/dang-nhap?err=true")
////				.defaultSuccessUrl("/", true)
////				.and()
////				.exceptionHandling().accessDeniedPage("/login");
//	}
//}
//
