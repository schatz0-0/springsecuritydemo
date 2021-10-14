package com.zime.consumerclient.component;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        Collection<? extends GrantedAuthority> auths = authentication.getAuthorities();
        for (ConfigAttribute configAttribute:configAttributes){
            if ("ROLE_LOGIN".equals(configAttribute.getAttribute()) && authentication instanceof UsernamePasswordAuthenticationToken){
                return;
            }
            for (GrantedAuthority grantedAuthority:auths){
                if (configAttribute.getAttribute().trim().equals(grantedAuthority.getAuthority()))
                    return;
            }
        }
        throw new AccessDeniedException("权限不足");
    }


    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
