package com.dormhub.controller;

import com.dormhub.model.Jurusan;
import com.dormhub.model.User;
import com.dormhub.service.JurusanService;
import com.dormhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private JurusanService jurusanService; // Tambahkan service untuk mengambil data jurusan

    @GetMapping("/daftar")
    public String daftarPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("jurusanList", jurusanService.getAllJurusan()); // Kirim daftar jurusan ke view
        return "daftar"; // File daftar.html
    }

    @PostMapping("/daftar")
    public String daftarUser(
            @ModelAttribute User user,
            @RequestParam("jurusanId") int jurusanId,
            Model model) {
        // Validasi jurusan
        Jurusan jurusan = jurusanService.findById(jurusanId);
        if (jurusan == null) {
            model.addAttribute("error", "Jurusan tidak ditemukan");
            return "daftar";
        }
    
        // Proses pendaftaran user
        String result = userService.registerUser(user, jurusan);
        if (result.equals("Berhasil mendaftar")) {
            return "redirect:/login"; // Redirect ke halaman login
        } else {
            model.addAttribute("error", result);
            return "daftar"; // Kembali ke halaman daftar jika ada error
        }
    }
    
}
