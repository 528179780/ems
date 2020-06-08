package com.sufu.ems.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Table(name = "se_user")
@Data
public class SeUser implements UserDetails {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    List<SeRole> roles;

    /**
     * 该账户是否可用
     */
    @Getter(AccessLevel.NONE)
    private Boolean enabled;

    /**
     * 该账户是否锁定
     */
    private Boolean locked;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (SeRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
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
        return true;
    }

    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(o == null||getClass()!=o.getClass())
            return false;
        SeUser seUser = (SeUser) o;
        return Objects.equals(username, seUser.username);//用户名是唯一的 因此比较username就可以
    }
    public int hashCode(){
        return Objects.hash(id,username,password,roles,enabled,locked);
    }
    public boolean hasRole(String role){
        for (SeRole seRole : roles) {
            if(seRole.getName().equals(role))
                return true;
        }
        return false;
    }
}