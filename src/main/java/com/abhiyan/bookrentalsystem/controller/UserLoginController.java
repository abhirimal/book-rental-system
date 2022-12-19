package com.abhiyan.bookrentalsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

    @GetMapping("/login")
    public String userLogin(){
        return "loginPage";
    }

    @GetMapping("/login-error")
    public String userLogin(Model model){
        model.addAttribute("loginError",true);
        return "loginPage";
    }

//    @PostMapping("/login-user")
//    public String userLoginNew(){
//        return "home";
//    }
}
