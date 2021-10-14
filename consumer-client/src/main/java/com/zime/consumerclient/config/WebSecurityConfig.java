package com.zime.consumerclient.config;

import com.zime.consumerclient.component.UrlAccessDecisionManager;
import com.zime.consumerclient.filter.CustomSecurityMetadataSource;
import com.zime.consumerclient.filter.JWTAuthenticationFilter;
import com.zime.consumerclient.filter.JWTAuthorizationFilter;
import com.zime.consumerclient.service.impl.JwtUserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private JwtUserServiceImpl jwtUserService;
    private CustomSecurityMetadataSource customSecurityMetadataSource;
    private UrlAccessDecisionManager urlAccessDecisionManager;

    public WebSecurityConfig(JwtUserServiceImpl jwtUserService,CustomSecurityMetadataSource customSecurityMetadataSource,UrlAccessDecisionManager urlAccessDecisionManager){
        this.jwtUserService = jwtUserService;
        this.customSecurityMetadataSource = customSecurityMetadataSource;
        this.urlAccessDecisionManager = urlAccessDecisionManager;
    }

    @Bean
    PasswordEncoder passwordEncoder(){

//        String username = "user";
//        String password = "123456";
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
//        String encodePassword = encoder.encode(password);
//        System.out.println(String.format("username:%s,password:%s",username,encodePassword));

        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(customSecurityMetadataSource);
                        object.setAccessDecisionManager(urlAccessDecisionManager);
                        return object;
                    }
                })
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/demo/download");
    }

}
