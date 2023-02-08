package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

@Controller
public class AdminController {

    private final UserServiceImp userServiceImp;
    private final RoleServiceImp roleServiceImp;

    @Autowired
    public AdminController(UserServiceImp userServiceImp, RoleServiceImp roleServiceImp) {
        this.userServiceImp = userServiceImp;
        this.roleServiceImp = roleServiceImp;
    }

    @GetMapping("/admin")
    public String getAdminPage(ModelMap modelMap) {
        modelMap.addAttribute("userList", userServiceImp.getUserList());
        return "admin";
    }

    @GetMapping("/new")
    public String getPageToAddNewUser(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        modelMap.addAttribute("roleList", roleServiceImp.getRoleList());
        return "new";
    }

    @PostMapping("/new")
    public String addNewUser(@ModelAttribute User user) {
        userServiceImp.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/update/{username}")
    public String getPageToUpdate(ModelMap modelMap, @PathVariable("username") String username) {
        modelMap.addAttribute("user", userServiceImp.findByUsername(username));
        modelMap.addAttribute("roleList", roleServiceImp.getRoleList());
        return "update";
    }

    @PatchMapping("/update/{username}")
    public String updateUser(@ModelAttribute User user, @PathVariable("username") String username) {
        userServiceImp.updateUser(username, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/update/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userServiceImp.deleteUser(id);
        return "redirect:/admin";
    }
}
