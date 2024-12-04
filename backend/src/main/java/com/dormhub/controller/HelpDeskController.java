package com.dormhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelpDeskController {

    @GetMapping("/help-desk/dashboard")
    public String helpDeskDashboard() {
        return "help-desk/dashboard"; // File HTML untuk dashboard help-desk
    }
}
