package com.abhiyan.bookrentalsystem.controller.user;

import com.abhiyan.bookrentalsystem.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String userLogin(){
        return "loginPage";
    }

    @GetMapping("/login-error")
    public String userLogin(Model model){
        model.addAttribute("loginError",true);
        return "loginPage";
    }
    @GetMapping("/dashboard")
    public String viewDashboard(){
        return "dashboard";
    }

    @GetMapping("/403")
    public String viewErrorPage(){
        return "accessDeniedPage";
    }
}
