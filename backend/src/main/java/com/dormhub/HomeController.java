package com.dormhub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String homePage() {
        return "Home"; // Nama file HTML di folder templates (tanpa ekstensi .html)
    }
}