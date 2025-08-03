package com.example.auth_service.PrepMate_AI.usermanagement.utility;

import com.example.auth_service.PrepMate_AI.usermanagement.db.models.User;
import com.example.auth_service.PrepMate_AI.usermanagement.service.impl.LoggedInUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private LoggedInUser loggedInUser;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);


    private static final List<String> EXCLUDE_URLS = List.of(
            "/prep-mate/api/user/login",
            "/prep-mate/api/user/register",
            "/prep-mate/api/health"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {

        String path = request.getRequestURI();
        String email="";
        String token="";


        for(String exclude : EXCLUDE_URLS)
        {
            if (pathMatcher.match(exclude, path)) {
                logger.info("Skipping JWT filter for path: {}", path);
                filterChain.doFilter(request, response);
                logger.info("After forwarding request for path: {}", path);
                return;
            }
        }

        final String authHeader = request.getHeader("Authorization");

        if(authHeader!=null && authHeader.startsWith("Bearer "))
        {
            token = authHeader.substring(7);
            try {
                email = jwtUtil.extractEmail(token);
            } catch (Exception e) {
                // handle token parsing error
            }
        }

        if(email!=null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            UserDetails userDetails = loggedInUser.loadUserByUsername(email);
            if (jwtUtil.validateToken(token, (User) userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);



    }
}
