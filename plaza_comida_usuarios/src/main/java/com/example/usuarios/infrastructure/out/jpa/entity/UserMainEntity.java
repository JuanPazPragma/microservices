package com.example.usuarios.infrastructure.out.jpa.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserMainEntity implements UserDetails {
    private String name;
    private String lastName;
    private String idNumber;
    private String phone;
    private String email;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    //Constructor
    public UserMainEntity(String name, String lastName, String idNumber, String email, String phone, String password, Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserMainEntity build(UserEntity userEntity) {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userEntity.getRolId().getName());
        List<GrantedAuthority> authorities = List.of(simpleGrantedAuthority);

        return new UserMainEntity(userEntity.getName(), userEntity.getLastName(),
                userEntity.getIdNumber(), userEntity.getEmail(),
                userEntity.getPhone(), userEntity.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}