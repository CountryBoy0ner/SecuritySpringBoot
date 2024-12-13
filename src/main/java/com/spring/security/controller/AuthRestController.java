package com.spring.security.controller;

import com.spring.security.dto.JwtRequest;
import com.spring.security.dto.JwtResponse;
import com.spring.security.dto.RegistrationUserDto;
import com.spring.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        JwtResponse jwtResponse = authService.createAuthToken(authRequest);// todo  add Exc Handler
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);// todo  add Exc Handler
    }
}
