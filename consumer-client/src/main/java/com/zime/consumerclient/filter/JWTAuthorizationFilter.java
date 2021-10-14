package com.zime.consumerclient.filter;

import com.zime.consumerclient.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(JwtUtil.TOKEN_HEADER);

        if (tokenHeader==null||!tokenHeader.startsWith(JwtUtil.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        super.doFilterInternal(request,response,chain);
    }

        private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader){

            String token = tokenHeader.replace(JwtUtil.TOKEN_PREFIX,"");
            String username = JwtUtil.getUsername(token);
            String role = JwtUtil.getUserRole(token);
            String[] roles = role.split(",");
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
            for (String s:roles){
                authorities.add(new SimpleGrantedAuthority(s));
            }
            if (username !=null){
                return new UsernamePasswordAuthenticationToken(username,null,authorities);
            }
            return null;
        }
}
