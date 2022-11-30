package com.example.board.vel01.service;

import com.example.board.vel01.base.jwt.JwtTokenProvider;
import com.example.board.vel01.domain.User;
import com.example.board.vel01.model.AddUserDto;
import com.example.board.vel01.model.LoginUserDto;
import com.example.board.vel01.model.response.AddUserResponse;
import com.example.board.vel01.model.response.LoginUserResponse;
import com.example.board.vel01.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserAccountService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AddUserResponse signUp(AddUserDto user) {
        String encodingPassword = encodingPassword(user.getPassword());
        User savedUser = userRepository.save(user.toEntity(encodingPassword));

        return new AddUserResponse(savedUser);
    }

    public LoginUserResponse signIn(LoginUserDto userDto) {
        User user = userRepository.findBynickName(userDto.getnickName())
                .orElseThrow(IllegalArgumentException::new);

        passwordMustBeSame(userDto.getPassword(), user.getPassword());

        String token = jwtTokenProvider.makeJwtToken(user);
        return new LoginUserResponse(token);
    }

    private void passwordMustBeSame(String requestPassword, String password) {
        if (!passwordEncoder.matches(requestPassword, password)) {
            throw new IllegalArgumentException();
        }
    }

    private String encodingPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
