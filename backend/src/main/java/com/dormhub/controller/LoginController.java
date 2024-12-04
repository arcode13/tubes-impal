package com.dormhub.controller;

import com.dormhub.model.User;
import com.dormhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "Login"; // File Login.html
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model) {

        User user = userService.authenticate(email, password);

        if (user != null) {
            String level = user.getLevel().getNama();
            switch (level.toLowerCase()) {
                case "mahasiswa":
                case "senior residence": // Redirect ke dashboard mahasiswa
                    return "redirect:/mahasiswa/dashboard";
                case "admin":
                    return "redirect:/admin/dashboard";
                case "help desk":
                    return "redirect:/help-desk/dashboard";
                default:
                    model.addAttribute("error", "Level user tidak valid");
                    return "Login";
            }
        } else {
            model.addAttribute("error", "Email atau password salah");
            return "Login";
        }
    }
}
