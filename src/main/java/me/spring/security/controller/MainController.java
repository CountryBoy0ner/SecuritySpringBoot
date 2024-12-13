package com.spring.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor            //TODO
public class MainController {
    @GetMapping("/unsecured")
    public String unsecuredPage() {
        return "unsecuredPage";
    }

    @GetMapping("/secured")
    public String securedPage() {
        return "securedPage";
    }
    @GetMapping("/admin")
    public String adminPage() {
        return "adminPage";
    }

    @GetMapping("/info")
    public String userPage(Principal principal) {
        return principal.getName();
    }
}
