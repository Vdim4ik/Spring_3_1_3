package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/update/{id}")
    public String getPageToUpdate(ModelMap modelMap, @PathVariable("id") long id) {
        modelMap.addAttribute("user", userService.getUserById(id));
        return "update";
    }

    @PatchMapping("/update/{id}")
    public String updateUser(@ModelAttribute User user,
                             @RequestParam("checkBoxRole") List<String> checkBox) {
        Set<Role> roles = new HashSet<>();
        for (String role: checkBox) {
            roles.add(roleService.getRoleByName(role));
        }
        user.setRoles(roles);
        userService.updateUser(user.getId(), user);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String getPageToAddNewUser(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/new")
    public String addNewUser(@ModelAttribute User user,
                             @RequestParam("checkBoxRole") List<String> checkBox) {
        Set<Role> roles = new HashSet<>();
        for (String role: checkBox) {
            roles.add(roleService.getRoleByName(role));
        }
        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/update/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
