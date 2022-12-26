package com.abhiyan.bookrentalsystem.controller.admin;

import com.abhiyan.bookrentalsystem.dto.MemberDto;
import com.abhiyan.bookrentalsystem.dto.ResponseDto;
import com.abhiyan.bookrentalsystem.model.Author;
import com.abhiyan.bookrentalsystem.model.Member;
import com.abhiyan.bookrentalsystem.repository.MemberRepo;
import com.abhiyan.bookrentalsystem.service.MemberService;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberRepo memberRepo, MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/register-member")
    public String addMember(Model model){
        MemberDto memberDto = new MemberDto();
        model.addAttribute("member",memberDto);
        return "admin/registerPage";
    }

    @PostMapping("/register-member/new")
    public String addNewMember(@Valid @ModelAttribute("member") MemberDto memberDto, BindingResult bindingResult,
                               Model model, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            model.addAttribute("memberDto",memberDto);
            return "admin/registerPage";
        }
        ResponseDto responseDto =         memberService.saveMember(memberDto);
        if(responseDto.getStatus()){
            redirectAttributes.addFlashAttribute("message",responseDto.getMessage());
            return "redirect:/admin/login";
        }
        model.addAttribute("errorMessage",responseDto.getMessage());
        return "admin/registerPage";

    }

    @GetMapping("/view-members")
    public String viewAllMembers(Model model){
        model.addAttribute("member",memberService.viewMembers());
        return "admin/member/viewMembers";
    }

    @GetMapping("/delete-member/{id}")
    public String deleteMemberById(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        memberService.deleteMember(id);
        redirectAttributes.addFlashAttribute("message","Member account deleted successfully.");
        return "redirect:/admin/view-members";
    }

    @GetMapping("/edit-member/{id}")
    public String editMember(@PathVariable Integer id, Model model){
        model.addAttribute("member",memberService.editMember(id));
        return "admin/member/updateMember";
    }

    @PostMapping("/update-member/{id}")
    public String updateMember(@PathVariable Integer id, @Valid @ModelAttribute("member") MemberDto memberDto,
                               BindingResult bindingResult,Model model, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            model.addAttribute("member",memberDto);
            return "admin/member/updateMember";
        }
        memberService.updateMember(id, memberDto);
        redirectAttributes.addFlashAttribute("message","Member account updated successfully.");
        return "redirect:/admin/view-members";
    }

    @PostMapping("/search-member")
    public String searchMember(@RequestParam(value="memberName", required=true) String memberName, Model model){
        List<Member> foundMember = memberService.searchMember(memberName);
        model.addAttribute("member",foundMember);
        return "admin/member/viewMembers";
    }
}







