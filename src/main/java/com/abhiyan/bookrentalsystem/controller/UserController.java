package com.abhiyan.bookrentalsystem.controller;

import com.abhiyan.bookrentalsystem.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/dashboard")
    public String viewDashboard(){
        return "dashboard";
    }

    @GetMapping("/403")
    public String viewErrorPage(){
        return "accessDeniedPage";
    }
}
