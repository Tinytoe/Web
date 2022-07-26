//package com.ducnt.projectAPI;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.ducnt.projectAPI.service.JwtTokenFilter;
//
//@SuppressWarnings("deprecation")
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true,
//	prePostEnabled = true, jsr250Enabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	UserDetailsService userDetailsService;
//	
//	@Autowired
//	private JwtTokenFilter jwtTokenFilter;
//
//	//xac thuc
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//	}
//	
//	
//	@Override
//	@Bean
//	protected AuthenticationManager authenticationManager() throws Exception {
//		return super.authenticationManager();
//	}
//		
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//				.antMatchers("/user/**")
//				// .hasAnyRole("ADMIN","SUBADMIN")//ROLE_
//				.hasAnyAuthority("ROLE_ADMIN")
//				.antMatchers("/bill/**").authenticated()
//				.antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
//				.anyRequest().permitAll().and()
//				.csrf().disable()
//				
//				.httpBasic();
////				.formLogin();
////				.loginPage("/dang-nhap")
////				.loginProcessingUrl("/login") //action 
////				.failureUrl("/dang-nhap?err=true")
////				.defaultSuccessUrl("/", false)
////				.and()
////				.exceptionHandling().accessDeniedPage("/deny");
//		
//		//Apply JWT
//		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
//	}
//}
//
