package me.spring.security.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor//TODO
public class MainController {
    @GetMapping("/unsecured")
    public String unsecuredPage() {
        return "unsecured";
    }


    @GetMapping("/sec")
    public String home() {
        return "sec";
    }
    @GetMapping("/loader")
    public String loaderPage() {
        return "loader";
    }


    @GetMapping("/secured")
    public String securedPage() {
        return "secured";
    }


    @GetMapping("/admin")
    public String adminPage() {
        return "adminPage";
    }

    @GetMapping("/login")
    public String loginPage(Model model, HttpServletRequest request) {
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            System.out.println(headerName + ": " + request.getHeader(headerName));
        });
        //model.addAttribute("error", null);
        return "login";
    }

    @GetMapping("/registration")
    public String registerPage() {
        return "registration";
    }

    @GetMapping("/info")
    public String userPage() {
        //return principal.getName();
        return "index";

    }

    @GetMapping("/index")
    public String indexPage() {
        return "index";
    }


}
