package com.abhiyan.bookrentalsystem.service.impl;

import com.abhiyan.bookrentalsystem.converter.MemberDtoConverter;
import com.abhiyan.bookrentalsystem.dto.MemberDto;
import com.abhiyan.bookrentalsystem.dto.ResponseDto;
import com.abhiyan.bookrentalsystem.enums.AccountState;
import com.abhiyan.bookrentalsystem.model.Member;
import com.abhiyan.bookrentalsystem.model.Role;
import com.abhiyan.bookrentalsystem.repository.MemberRepo;
import com.abhiyan.bookrentalsystem.repository.RoleRepo;
import com.abhiyan.bookrentalsystem.service.MemberService;
import com.abhiyan.bookrentalsystem.service.services.EmailSenderService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepo memberRepo;
    private final MemberDtoConverter memberDtoConverter;

    private final EmailSenderService emailSenderService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final RoleRepo roleRepo;

    public MemberServiceImpl(MemberRepo memberRepo, MemberDtoConverter memberDtoConverter, EmailSenderService emailSenderService, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepo roleRepo) {
        this.memberRepo = memberRepo;
        this.memberDtoConverter = memberDtoConverter;
        this.emailSenderService = emailSenderService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepo = roleRepo;
    }

    @Override
    public ResponseDto saveMember(MemberDto memberDto) {


        try{
            Member existingDeletedMember = memberRepo.findDeletedStateMember(memberDto.getEmail());
            if(existingDeletedMember!=null){
                existingDeletedMember.setAccountState(AccountState.ACTIVE);
                existingDeletedMember.setMobileNumber(memberDto.getMobileNumber());
                existingDeletedMember.setAddress(memberDto.getAddress());
                memberRepo.save(existingDeletedMember);

                return ResponseDto.builder()
                        .status(true)
                        .message("Member added successfully")
                        .build();
            }
            Member member = memberDtoConverter.dtoToEntity(memberDto);
            member.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));

            List<Role> role = roleRepo.getUserRole();
            member.setRoles(role);

            member.setAccountState(AccountState.ACTIVE);

            memberRepo.save(member);
            return ResponseDto.builder()
                    .status(true)
                    .message("Member registered successfully. Please log in to access your account.")
                    .build();
        }
        catch (Exception e){

            if(e.getMessage().contains("email")){
                return ResponseDto.builder()
                        .status(false)
                        .message("Member already exists for given email address.")
                        .build();
            }
                if(e.getMessage().contains("username")){
                    return ResponseDto.builder()
                            .status(false)
                            .message("Member already exists for given username.")
                            .build();
                }

            else{
                e.printStackTrace();
                return ResponseDto.builder()
                        .status(false)
                        .message(e.getMessage())
                        .build();
            }

        }

        //send email
//        emailSenderService.sendEmail(member.getEmail(),
//                "Hello "+member.getName()+", \n" +
//                        "Your account has been created in Book Rental System \n"+
//                        "Thank You.",
//                "Account created in Book Rental");
    }

    @Override
    public List<MemberDto> viewMembers() {
        List<Member> members = memberRepo.findAllActiveMember();
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
    memberRepo.softDeleteMemberById(id);
    }

    @Override
    public List<Member> searchMember(String memberName) {
        return memberRepo.findAllByName(memberName);
    }
}
