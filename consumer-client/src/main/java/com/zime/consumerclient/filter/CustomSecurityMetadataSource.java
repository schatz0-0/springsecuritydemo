package com.zime.consumerclient.filter;

import com.zime.consumerclient.mapper.MenuMapper;
import com.zime.consumerclient.pojo.Menu;
import com.zime.consumerclient.pojo.Role;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    private MenuMapper menuMapper;

    public CustomSecurityMetadataSource(MenuMapper menuMapper){
        this.menuMapper=menuMapper;
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        String requestUrl = ((FilterInvocation)object).getRequestUrl();
        List<Menu> allMenus = menuMapper.getAllMenus();

        List<String> roleList = new ArrayList<String>();
        for (Menu menu:allMenus){
            System.out.println(menu.getPattern());
            if (antPathMatcher.match(menu.getPattern(),requestUrl)){
                System.out.println("匹配通过的Pattern："+menu.getPattern()+" 请求路径："+requestUrl);
                List<Role> roles = menu.getRoles();
                System.out.println(roles);
                for (int i=0;i<roles.size();i++){
                    roleList.add(roles.get(i).getValue());
                }
            }
        }
        System.out.println(roleList);
        String[] roleArray=(String[]) roleList.toArray(new String[0]);
        if (roleList.size()>0)
            return SecurityConfig.createList(roleArray);
        if (requestUrl.contains("/login"))
            return SecurityConfig.createList("ROLE_LOGIN");
        else
            return SecurityConfig.createList("ROLE_UNNOWN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
