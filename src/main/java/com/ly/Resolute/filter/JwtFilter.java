package com.ly.Resolute.filter;

import com.ly.Resolute.repository.UserRepo;
import com.ly.Resolute.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // get the authorization header and validate
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasText(header) || StringUtils.hasText(header) && !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Authorization -> Bearer <token>
        final String token = header.split(" ")[1].trim();

        // get user identity and set it on the spring security context
        UserDetails test = userRepo
                .findByUsername(jwtUtil.getUsernameFromToken(token))
                .orElse(null);
        CustomUserDetails userDetails = new CustomUserDetails(userRepo.findByUsername(jwtUtil.getUsernameFromToken(token)).orElse(null), jwtUtil.getAuthorityFromToken(token));
        System.out.println(userDetails.getAuthorities());
        System.out.println(userDetails.getUsername());

        // get jwt token and validate
        if (!jwtUtil.validateToken(token, userDetails)) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ?
                        List.of() : userDetails.getAuthorities()
        );

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);

    }
}
