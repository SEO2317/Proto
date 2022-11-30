package com.example.board.vel01.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginUserDto {

    private Long id;
    private String password;

    public LoginUserDto(Long id, String password) {
        this.id = id;
        this.password = password;
    }
}
