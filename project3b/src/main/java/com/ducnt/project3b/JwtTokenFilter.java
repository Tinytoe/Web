//package com.ducnt.project3b;
//
//import com.ducnt.project3b.service.JwtTokenService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Service
//public class JwtTokenFilter extends OncePerRequestFilter {
//    @Autowired
//    private JwtTokenService jwtTokenProvider;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        String token = resolveToken(httpServletRequest);
//
//        try {
//            if (token != null && jwtTokenProvider.validateToke(token)) {
//                Authentication auth = jwtTokenProvider.getAuthentication(token);
//
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            }
//        } catch (Exception ex) {
//            SecurityContextHolder.clearContext();
//            httpServletResponse.sendError(401, ex.getMessage());
//            return;
//        }
//    }
//
//    public String resolveToken(HttpServletRequest req){
//        String bearerToken = req.getHeader("Authorization");
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")){
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//}
