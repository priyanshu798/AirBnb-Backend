package com.Priyanshu.ainBnb.service;

import com.Priyanshu.ainBnb.dto.LogInDto;
import com.Priyanshu.ainBnb.dto.SignUpRequestDto;
import com.Priyanshu.ainBnb.dto.UserDto;
import com.Priyanshu.ainBnb.entity.User;
import com.Priyanshu.ainBnb.entity.enums.Role;
import com.Priyanshu.ainBnb.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserDto signup(SignUpRequestDto signUpRequestDto) {
        User user = userService.getUserByEmail(signUpRequestDto.getEmail());

        if (user != null) {
            throw new RuntimeException("User is already present with same email id");

        }
        User newUser = modelMapper.map(signUpRequestDto, User.class);
        newUser.setRoles(Set.of(Role.GUEST));
        newUser.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        newUser = userService.saveUser(newUser);

        return modelMapper.map(newUser, UserDto.class);
    }

    public String[] login(LogInDto logInDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                logInDto.getEmail(), logInDto.getPassword()
        ));

        User user = (User) authentication.getPrincipal();
        String[] tokens = new String[2];
        tokens[0] = jwtService.generateAccessToken(user);
        tokens[1] = jwtService.generateRefreshToken(user);

        return tokens;
    }
    public String refreshToken(String refreshToken) {
        Long id = jwtService.getUserIdFromToken(refreshToken);

        User user = userService.getUserById(id);

        return jwtService.generateAccessToken(user);
    }
}
