package com.example.testtask.security.service;

import com.example.testtask.constant.Constants;
import com.example.testtask.model.User;
import com.example.testtask.security.model.AuthenticationRequest;
import com.example.testtask.security.model.AuthenticationResponse;
import com.example.testtask.security.model.RegisterRequest;
import com.example.testtask.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtTokenService tokenUtil;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = userService.save(new User(request.getName(), request.getAge()));
        String token = tokenUtil.generateToken(new org.springframework.security.core.userdetails.User(
                user.getName(),
                String.valueOf(user.getAge()),
                Collections.singletonList(new SimpleGrantedAuthority(Constants.DEFAULT_ROLE)))
        );
        return AuthenticationResponse
                .builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getName(),
                        request.getAge()
                )
        );
        User user = userService.findByName(request.getName());
        String token = tokenUtil.generateToken(new org.springframework.security.core.userdetails.User(
                user.getName(),
                String.valueOf(user.getAge()),
                Collections.singletonList(new SimpleGrantedAuthority(Constants.DEFAULT_ROLE)))
        );
        return AuthenticationResponse
                .builder()
                .token(token)
                .build();
    }
}