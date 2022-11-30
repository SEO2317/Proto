package com.example.board.vel01.repository;

import com.example.board.vel01.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findBynickName(String nickName);
}