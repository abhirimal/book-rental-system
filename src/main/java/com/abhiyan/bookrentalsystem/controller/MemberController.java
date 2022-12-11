package com.abhiyan.bookrentalsystem.controller;

import com.abhiyan.bookrentalsystem.dto.MemberDto;
import com.abhiyan.bookrentalsystem.repository.MemberRepo;
import com.abhiyan.bookrentalsystem.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String addNewMember(@Valid @ModelAttribute MemberDto memberDto, BindingResult bindingResult,
                               Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("memberDto",memberDto);
            return "member/registerMember";
        }
        memberService.saveMember(memberDto);
        return "redirect:/view-members";
    }


}
