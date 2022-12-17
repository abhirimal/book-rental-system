package com.abhiyan.bookrentalsystem.service.impl;

import com.abhiyan.bookrentalsystem.converter.MemberDtoConverter;
import com.abhiyan.bookrentalsystem.dto.MemberDto;
import com.abhiyan.bookrentalsystem.model.Member;
import com.abhiyan.bookrentalsystem.repository.MemberRepo;
import com.abhiyan.bookrentalsystem.service.MemberService;
import com.abhiyan.bookrentalsystem.service.services.EmailSenderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepo memberRepo;
    private final MemberDtoConverter memberDtoConverter;

    private final EmailSenderService emailSenderService;

    public MemberServiceImpl(MemberRepo memberRepo, MemberDtoConverter memberDtoConverter, EmailSenderService emailSenderService) {
        this.memberRepo = memberRepo;
        this.memberDtoConverter = memberDtoConverter;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public void saveMember(MemberDto memberDto) {
        Member member = memberDtoConverter.dtoToEntity(memberDto);
        memberRepo.save(member);

        //send email
        emailSenderService.sendEmail(member.getEmail(),
                "Hello "+member.getName()+", \n" +
                        "Your account has been created in Book Rental System \n"+
                        "Thank You.",
                "Account created in Book Rental");
    }

    @Override
    public List<MemberDto> viewMembers() {
        List<Member> members = memberRepo.findAll();
        List<MemberDto> memberDtos = memberDtoConverter.entityToDto(members);
        return memberDtos;
    }

    @Override
    public MemberDto editMember(Integer id) {
        Member member = memberRepo.findById(id).orElse(null);
        MemberDto memberDto = memberDtoConverter.entityToDto(member);
        return memberDto;
    }

    @Override
    public MemberDto updateMember(Integer id, MemberDto memberDto) {
        Member existingMember = memberRepo.findById(id).orElse(null);
        existingMember.setName(memberDto.getName());
        existingMember.setEmail(memberDto.getEmail());
        existingMember.setAddress(memberDto.getAddress());
        existingMember.setMobileNumber(memberDto.getMobileNumber());
        memberRepo.save(existingMember);
        return memberDto;

    }

    @Override
    public void deleteMember(Integer id) {
    memberRepo.deleteById(id);
    }
}
