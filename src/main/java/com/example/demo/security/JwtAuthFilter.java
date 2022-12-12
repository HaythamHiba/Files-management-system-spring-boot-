package com.example.demo.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl applicationUserService;
    private final JwtUtils jwtUtils;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {




        final String authHeader=request.getHeader("Authorization");
        final String username;
        final String jwtToken;


        if( authHeader == null|| !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;

        }

        jwtToken=authHeader.substring(7);

        username=jwtUtils.extractUsername(jwtToken);

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails userDetails=applicationUserService.loadUserByUsername(username);

            if(jwtUtils.validateToken(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken authToken=
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }else {
                throw new IllegalStateException("Login First");
            }

        }
        filterChain.doFilter(request,response);








    }
}
