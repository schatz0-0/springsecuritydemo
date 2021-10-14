package com.zime.consumerclient.filter;

import com.zime.consumerclient.mode.JwtUser;
import com.zime.consumerclient.mode.Result;
import com.zime.consumerclient.mode.ReturnCode;
import com.zime.consumerclient.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zime.consumerclient.vo.ResultGenerator;
import com.zime.consumerclient.vo.ResultVo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        JwtUser user = new JwtUser();
        try {
            user = new ObjectMapper().readValue(request.getInputStream(),JwtUser.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(),user.getPassword()
                        ));
    }

    public void successfulAuthentication(HttpServletRequest request,
                                         HttpServletResponse response,
                                         FilterChain chain,
                                         Authentication authResult)
    throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        StringBuffer stringBuffer = new StringBuffer();
        for (GrantedAuthority grantedAuthority:authorities){
            stringBuffer.append(grantedAuthority.getAuthority()).append(",");
        }
        if (stringBuffer.length() >0)
            stringBuffer.deleteCharAt(stringBuffer.length()-1);
        String jwt = JwtUtil.TOKEN_PREFIX+
                JwtUtil.createToken(authResult.getName(),stringBuffer.toString());
        System.out.println(String.format("用户：%s,权限：%s,令牌：%s",authResult.getName(),stringBuffer.toString(),jwt));
        JwtUser user = (JwtUser) authResult.getPrincipal();
        user.setToken(jwt);
        ResultVo<JwtUser> jwtUserResultVo = ResultGenerator.genSuccessResult(user);
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(new ObjectMapper().writeValueAsString(jwtUserResultVo));
        printWriter.flush();
        printWriter.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResultVo<Object> resultVo = ResultGenerator.genFailResult("登录失败");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        printWriter.write(new ObjectMapper().writeValueAsString(resultVo));
        printWriter.flush();
        printWriter.close();
    }
}
