package com.abhiyan.bookrentalsystem.controller;

import com.abhiyan.bookrentalsystem.dto.MemberDto;
import com.abhiyan.bookrentalsystem.repository.MemberRepo;
import com.abhiyan.bookrentalsystem.service.MemberService;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberRepo memberRepo, MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/add-member")
    public String addBook(Model model){
        MemberDto memberDto = new MemberDto();
        model.addAttribute("member",memberDto);
        return "member/registerMember";
    }

    @PostMapping("/add-member/new")
    public String addNewMember(@Valid @ModelAttribute("member") MemberDto memberDto, BindingResult bindingResult,
                               Model model, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            model.addAttribute("memberDto",memberDto);
            return "member/registerMember";
        }
        memberService.saveMember(memberDto);
        redirectAttributes.addFlashAttribute("message","Member account created successfully.");

        return "redirect:/view-members";
    }

    @GetMapping("/view-members")
    public String viewAllMembers(Model model){
        model.addAttribute("member",memberService.viewMembers());
        return "member/viewMembers";
    }

    @GetMapping("/delete-member/{id}")
    public String deleteMemberById(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        memberService.deleteMember(id);
        redirectAttributes.addFlashAttribute("message","Member account deleted successfully.");
        return "redirect:/view-members";
    }

    @GetMapping("/edit-member/{id}")
    public String editMember(@PathVariable Integer id, Model model){
        model.addAttribute("member",memberService.editMember(id));
        return "member/updateMember";
    }

    @PostMapping("/update-member/{id}")
    public String updateMember(@PathVariable Integer id, @Valid @ModelAttribute("member") MemberDto memberDto,
                               BindingResult bindingResult,Model model, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            model.addAttribute("member",memberDto);
            return "member/updateMember";
        }
        memberService.updateMember(id, memberDto);
        redirectAttributes.addFlashAttribute("message","Member account updated successfully.");
        return "redirect:/view-members";
    }
}







