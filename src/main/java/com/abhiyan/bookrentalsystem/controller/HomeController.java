package com.abhiyan.bookrentalsystem.controller;
import com.abhiyan.bookrentalsystem.dto.MemberDto;
import com.abhiyan.bookrentalsystem.dto.NewPasswordRequestDto;
import com.abhiyan.bookrentalsystem.dto.ResponseDto;
import com.abhiyan.bookrentalsystem.service.MemberService;
import org.springframework.boot.Banner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
            return "userRegisterPage";
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
    public String userLogin(Model model,
                            RedirectAttributes redirectAttributes) throws UsernameNotFoundException{

        try{
        redirectAttributes.addFlashAttribute("loginError","User is gg");
        model.addAttribute("loginError",true);}
        catch (Exception e){
            redirectAttributes.addFlashAttribute("loginError",e.getMessage());
        }
        return "loginPage";
    }
    @GetMapping("/dashboard")
    public String viewDashboard(Model model, Principal principal){
        String username = principal.getName();
        MemberDto member = memberService.viewMemberByUsername(username);
        model.addAttribute("member",member);
        return "dashboard";
    }

    // spring le afai handle garcha
//    @GetMapping("/403")
//    public String viewErrorPage(){
//        return "error/accessDeniedPage";
//    }


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
        return "adminRegisterPage";

    }

    @GetMapping("/forget-password")
    public String forgetPasswordLandingPage(){
        return "passwordReset/forgetPassword";
    }

    @PostMapping("/forget-password-useremail")
    public String forgetPasswordSubmitEmail(@RequestParam(value = "email", required = true) String email,
                                            Model model, RedirectAttributes redirectAttributes){
        memberService.resetPassword(email);
        return "passwordReset/checkEmail";
    }

    @GetMapping("/verify-reset-password/{id}/{token}")
    public String verifyLink(@PathVariable Integer id, @PathVariable String token,
                             RedirectAttributes redirectAttributes, Model model){

        NewPasswordRequestDto password = new NewPasswordRequestDto();
        ResponseDto responseDto = memberService.verifyResetLink(id, token);

        if(responseDto.getStatus()){
            model.addAttribute("userInfo",id);
            model.addAttribute("message",responseDto.getMessage());
            model.addAttribute("passwordDto",password);
            return "passwordReset/enterNewPassword";
        }
        model.addAttribute("message",responseDto.getMessage());

        return "passwordReset/forgetPassword";
    }

    @PostMapping("/new-password-verification/{id}")
    public String passwordResetVerify(@PathVariable int id,
                                      @Valid @ModelAttribute("passwordDto") NewPasswordRequestDto passwordDto,
                                      BindingResult bindingResult,RedirectAttributes redirectAttributes,
                                      Model model){

        System.out.println(passwordDto.getPassword());

        if(bindingResult.hasErrors()){
            model.addAttribute("passwordDto",passwordDto);
            model.addAttribute("userInfo",id);
            return "passwordReset/enterNewPassword";
        }
        ResponseDto responseDto = memberService.passwordResetVerify(passwordDto.getPassword(), id);

        if(responseDto.getStatus()){
            model.addAttribute("password",responseDto.getMessage());
            return "passwordReset/passwordResetSuccess";
        }
        redirectAttributes.addFlashAttribute("errorMessage","Failed");
        return "passwordReset/enterNewPassword";
    }


}
