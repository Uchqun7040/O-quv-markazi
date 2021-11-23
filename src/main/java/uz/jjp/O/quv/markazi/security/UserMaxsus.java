package uz.jjp.O.quv.markazi.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.jjp.O.quv.markazi.entity.User;


import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class UserMaxsus implements UserDetails {
    private User user;

    public UserMaxsus() {
    }

    public UserMaxsus(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority auth=new SimpleGrantedAuthority(user.getLavozim());

        return Collections.singletonList(auth);
    }

    @Override
    public String getPassword() {
        return user.getParol();
    }

    @Override
    public String getUsername() {

        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
