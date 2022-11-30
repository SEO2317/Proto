package com.example.board.vel01.api;

import com.example.board.vel01.model.AddUserDto;
import com.example.board.vel01.model.LoginUserDto;
import com.example.board.vel01.model.UserDto;
import com.example.board.vel01.model.response.AddUserResponse;
import com.example.board.vel01.model.response.LoginUserResponse;
import com.example.board.vel01.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class userApi {

    private final UserAccountService userAccountService;

    @PostMapping("/sign-up")
    public ResponseEntity<AddUserResponse> signUp(@RequestBody AddUserDto addUserDto) {
        AddUserResponse savedUser = userAccountService.signUp(addUserDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedUser);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<LoginUserResponse> signIn(
            @RequestBody LoginUserDto loginUserDto) {
        LoginUserResponse loginUserResponse = userAccountService.signIn(loginUserDto);

        return ResponseEntity.ok()
                .body(loginUserResponse);
    }


    @GetMapping("/hello")
    public String hello(@AuthenticationPrincipal UserDto user) {
        return user.getId() + ", 안녕하세요!";
    }

}