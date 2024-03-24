package com.Classroom.Classroom.Security.JWTS;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtsAuthenticationFilter extends OncePerRequestFilter {

    private JwtUtils jwtUtils;
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token=getHeaderToken(request);

        if(StringUtils.hasText(token)&& jwtUtils.isValid(token)){
            UserDetails userDetails=userDetailsService.loadUserByUsername(
                    jwtUtils.getUsername(token)
            );

            UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(),
                    userDetails.getPassword(),
                    userDetails.getAuthorities()
            );

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authToken);

        }
        filterChain.doFilter(request,response);


    }

    private String getHeaderToken(HttpServletRequest request){
        String authHeader=request.getHeader("Authorization");

        if(StringUtils.hasText(authHeader)&&authHeader.startsWith("Bearer ")){
            String token=authHeader.substring(7);

            return token;
        }
        return  null;
    }
}
