package me.spring.security.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.spring.security.dto.JwtRequest;
import me.spring.security.dto.JwtResponse;
import me.spring.security.dto.RegistrationUserDto;
import me.spring.security.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:8080") // укажите ваш адрес
public class AuthRestController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest,  Model model) {
        JwtResponse jwtResponse = authService.createAuthToken(authRequest);
        return ResponseEntity.ok(jwtResponse);
    }

        @PostMapping("/reregistration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) { //todo put in another Class
        return authService.createNewUser(registrationUserDto);
    }
}
