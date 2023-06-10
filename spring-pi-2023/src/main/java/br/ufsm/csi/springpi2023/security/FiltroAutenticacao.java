package br.ufsm.csi.springpi2023.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FiltroAutenticacao extends OncePerRequestFilter {
    @Autowired private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String url = request.getRequestURI();
        System.out.println("filtro requisicao: "+url);

        try{

            if(!url.contains("login")){

                String token = request.getHeader("Authorization");
                System.out.println("Token: "+token);
                String username = new JWTUtil().getUsernameToken(token);
                System.out.println("username: "+username);
                System.out.println("Token expirado? "+ new JWTUtil().isTokenExpirado(token));

                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                    if(!new JWTUtil().isTokenExpirado(token)){

                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }
            filterChain.doFilter(request, response);

        }catch (ExpiredJwtException e){
            System.out.println("caiu no AccessDaniedException");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Expirado");
        }
    }
}
