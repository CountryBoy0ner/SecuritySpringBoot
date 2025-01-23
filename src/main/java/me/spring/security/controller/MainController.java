package me.spring.security.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor//TODO
public class MainController {
    @GetMapping("/unsecured")
    public String unsecuredPage() {
        return "unsecuredPage";
    }

    @GetMapping("/secured")
    public String securedPage(HttpServletRequest request) {
//         Получаем заголовок Authorization
        String authorizationHeader = request.getHeader("Authorization");

//         Логируем значение заголовка
        System.out.println("Authorization Header: " + authorizationHeader);

         //Вывод всех заголовков запроса
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            System.out.println(headerName + ": " + request.getHeader(headerName));
        });

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

//    @GetMapping("/")
//    public String home() {
//        return "index";
//    }
}
