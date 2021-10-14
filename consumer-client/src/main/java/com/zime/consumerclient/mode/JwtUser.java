package com.zime.consumerclient.mode;

import com.zime.consumerclient.mode.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2021-05-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtUser implements UserDetails {

    private Integer userId;

    private String username;

    private String password;

    private String realName;

    private String desc;

    private String token;

    @Getter(value = AccessLevel.NONE)
    private Boolean enabled;

    private Boolean locked;

    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        for (Role role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getValue()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
