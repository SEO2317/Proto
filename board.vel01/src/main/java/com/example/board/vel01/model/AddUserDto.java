package com.example.board.vel01.model;

import com.example.board.vel01.domain.User;

public class AddUserDto {
        private String nickName;
        private String pwd;

        public User toEntity(String encodePassword) {
            return User.builder()
                    .nickName(nickName)
                    .pwd(encodePassword)
                    .build();
        }
    }
