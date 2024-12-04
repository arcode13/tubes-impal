package com.dormhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MahasiswaController {

    @GetMapping("/mahasiswa/dashboard")
    public String mahasiswaDashboard() {
        return "mahasiswa/dashboard"; // File HTML untuk dashboard mahasiswa
    }
}
