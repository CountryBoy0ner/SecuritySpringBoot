package com.spring.security.controller;

import com.spring.security.dto.JwtRequest;
import com.spring.security.dto.JwtResponse;
import com.spring.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthPageController {

    private final AuthService authService;

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Шаблон страницы логина
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            JwtRequest authRequest = new JwtRequest(username, password);
            JwtResponse jwtResponse = authService.createAuthToken(authRequest);

            model.addAttribute("username", username);
            model.addAttribute("token", jwtResponse.getToken());

            return "welcome"; // Шаблон для приветствия после успешной аутентификации
        } catch (Exception e) {
            model.addAttribute("error", "Неверный логин или пароль.");
            return "login"; // Возвращаем на страницу логина с ошибкой
        }
    }
}
