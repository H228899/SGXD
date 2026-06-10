package com.example.course.controller;
import com.example.course.entity.User;
import com.example.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        User user = userService.login(username, password);
        if (user != null) {
            return "redirect:/course/list";
        }
        return "redirect:/login.html";
    }
}