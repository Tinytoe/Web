package com.ducnt.projectdemo.service;
//package com.ducnt.projectAPI.service;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//@Service
//public class JwtTokenFilter extends OncePerRequestFilter {
//
//	@Autowired
//	LoginService loginService;
//
//	@Autowired
//	private JwtTokenProvider jwtTokenProvider;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
//			FilterChain filterChain) throws ServletException, IOException {
//		String token = resolveToken(httpServletRequest);
//
//		if (token != null && jwtTokenProvider.validateToken(token)) {
//			Authentication auth = jwtTokenProvider.getAuthentication(token);
//			SecurityContextHolder.getContext().setAuthentication(auth);
//
//			filterChain.doFilter(httpServletRequest, httpServletResponse);
//		}
//	}
//
//	public String resolveToken(HttpServletRequest req) {
//		String bearerToken = req.getHeader("Authorization");
//		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//			return bearerToken.substring(7);
//		}
//		return null;
//	}
//
//}
