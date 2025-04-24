package com.ivanpolovnev.spring_security_app.controllers;

import com.ivanpolovnev.spring_security_app.entity.User;
import com.ivanpolovnev.spring_security_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAdminPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("newUser", new User()); // Для формы добавления нового пользователя
        return "admin";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("newUser") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
