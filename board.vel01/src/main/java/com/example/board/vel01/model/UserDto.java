package com.example.board.vel01.model;

import io.jsonwebtoken.Claims;
import java.util.Collection;
import java.util.Collections;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
public class UserDto {

    private final Long id;

    public UserDto(Claims claims) {
        this.id = claims.get("id", Long.class);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }
}