package com.spring.security.service;

import com.spring.security.dto.JwtRequest;
import com.spring.security.dto.JwtResponse;
import com.spring.security.dto.RegistrationUserDto;
import com.spring.security.dto.UserDto;
import com.spring.security.exceptions.AppError;
import com.spring.security.exceptions.customException.PasswordsDoNotMatchException;
import com.spring.security.exceptions.customException.UserAlreadyExistsException;
import com.spring.security.model.User;
import com.spring.security.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public JwtResponse createAuthToken(@RequestBody JwtRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return new JwtResponse(token);
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) { //
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            throw new PasswordsDoNotMatchException("Пароли не совпадают");
        }
        if (userService.findByUsername(registrationUserDto.getUserName()).isPresent()) { //todo sql запрос что бы не вытаскивать user`а
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существуе");
        }
        User user = userService.createNewUser(registrationUserDto);
        return ResponseEntity.ok(new UserDto(user.getId(), user.getUserName(), user.getEmail()));
    }
}
