package com.abhiyan.bookrentalsystem.controller;
import com.abhiyan.bookrentalsystem.dto.MemberDto;
import com.abhiyan.bookrentalsystem.dto.ResponseDto;
import com.abhiyan.bookrentalsystem.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {

    private final MemberService memberService;

    public HomeController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/home")
    public String viewHome(){
        return "home";
    }

    @GetMapping("/register-member")
    public String addMember(Model model){
        MemberDto memberDto = new MemberDto();
        model.addAttribute("member",memberDto);
        return "userRegisterPage";
    }

    @PostMapping("/register-member/new")
    public String addNewMember(@Valid @ModelAttribute("member") MemberDto memberDto, BindingResult bindingResult,
                               Model model, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            model.addAttribute("memberDto",memberDto);
            return "registerPage";
        }
        ResponseDto responseDto = memberService.saveMember(memberDto);
        if(responseDto.getStatus()){
            redirectAttributes.addFlashAttribute("message",responseDto.getMessage());
            return "redirect:/login";
        }
        model.addAttribute("errorMessage",responseDto.getMessage());
        return "userRegisterPage";

    }

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
    public String viewDashboard(Model model, Principal principal){
        String username = principal.getName();
        MemberDto member = memberService.viewMemberByUsername(username);
        model.addAttribute("member",member);
        return "dashboard";
    }

    @GetMapping("/403")
    public String viewErrorPage(){
        return "accessDeniedPage";
    }


    @GetMapping("/register-admin")
    public String addAdmin(Model model){
        MemberDto memberDto = new MemberDto();
        model.addAttribute("member",memberDto);
        return "adminRegisterPage";
    }

    @PostMapping("/register-admin/new")
    public String addNewAdmin(@Valid @ModelAttribute("member") MemberDto memberDto, BindingResult bindingResult,
                               Model model, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            model.addAttribute("memberDto",memberDto);
            return "adminRegisterPage";
        }
        ResponseDto responseDto = memberService.saveAdmin(memberDto);
        if(responseDto.getStatus()){
            redirectAttributes.addFlashAttribute("message",responseDto.getMessage());
            return "redirect:/login";
        }
        model.addAttribute("errorMessage",responseDto.getMessage());
        return "AdminRegisterPage";

    }
}
