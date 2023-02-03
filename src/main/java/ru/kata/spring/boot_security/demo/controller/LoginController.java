package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.model.User;

import java.security.Principal;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getAdminPage(ModelMap modelMap) {
        modelMap.addAttribute("userList", userService.getUserList());
        return "admin";
    }

    @GetMapping("/user")
    public String getUserPage(Principal principal, ModelMap modelMap) {
        User user = userService.getUserByUsername(principal.getName());
        modelMap.addAttribute("user", user);
        return "user";
    }

}
