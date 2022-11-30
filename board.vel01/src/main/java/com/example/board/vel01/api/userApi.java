package com.example.board.vel01.api;

import com.example.board.vel01.base.jwt.JwtTokenProvider;
import com.example.board.vel01.domain.User;
import com.example.board.vel01.model.AddUserDto;
import com.example.board.vel01.model.LoginUserDto;

import com.example.board.vel01.model.response.AddUserResponse;
import com.example.board.vel01.model.response.LoginUserResponse;
import com.example.board.vel01.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class userApi {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private final UserAccountService userAccountService;

    @PostMapping("/sign-up")
    public ResponseEntity<AddUserResponse> signUp(@RequestBody AddUserDto addUserDto) {
        AddUserResponse savedUser = userAccountService.signUp(addUserDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedUser);
    }

    @PostMapping("/sign-in")
    public String signIn(
            @RequestBody LoginUserDto loginUserDto) {
//        LoginUserResponse loginUserResponse = userAccountService.signIn(loginUserDto);

        User user = userAccountService.signIn(loginUserDto);
        final String token = jwtTokenProvider.makeJwtToken(user);
        return token;

//                ResponseEntity.ok()
//                        .body(user);


    }

}