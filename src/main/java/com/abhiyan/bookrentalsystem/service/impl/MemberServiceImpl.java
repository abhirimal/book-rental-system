package com.abhiyan.bookrentalsystem.service.impl;

import com.abhiyan.bookrentalsystem.converter.MemberDtoConverter;
import com.abhiyan.bookrentalsystem.dto.MemberDto;
import com.abhiyan.bookrentalsystem.dto.ResponseDto;
import com.abhiyan.bookrentalsystem.enums.AccountState;
import com.abhiyan.bookrentalsystem.model.Member;
import com.abhiyan.bookrentalsystem.model.PasswordResetCode;
import com.abhiyan.bookrentalsystem.model.Role;
import com.abhiyan.bookrentalsystem.repository.MemberRepo;
import com.abhiyan.bookrentalsystem.repository.PasswordResetCodeRepo;
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

    private final com.abhiyan.bookrentalsystem.service.services.PasswordResetCode passwordResetCode;

    private final EmailSenderService emailSenderService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final RoleRepo roleRepo;

    private final PasswordResetCodeRepo passwordResetCodeRepo;

    public MemberServiceImpl(MemberRepo memberRepo, MemberDtoConverter memberDtoConverter, com.abhiyan.bookrentalsystem.service.services.PasswordResetCode passwordResetCode, EmailSenderService emailSenderService, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepo roleRepo, PasswordResetCodeRepo passwordResetCodeRepo) {
        this.memberRepo = memberRepo;
        this.memberDtoConverter = memberDtoConverter;
        this.passwordResetCode = passwordResetCode;
        this.emailSenderService = emailSenderService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepo = roleRepo;
        this.passwordResetCodeRepo = passwordResetCodeRepo;
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

            emailSenderService.sendEmail(member.getEmail(),
                    "Hello "+member.getName()+", \n" +
                            "Your account has been created in Book Rental System \n"+
                            "Thank You.",
                    "Account created in Book Rental");

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

//        send email

    }

    @Override
    public ResponseDto saveAdmin(MemberDto memberDto) {



        try{
            Member existingDeletedMember = memberRepo.findDeletedStateMember(memberDto.getEmail());
            if(existingDeletedMember!=null){
                existingDeletedMember.setAccountState(AccountState.ACTIVE);
                existingDeletedMember.setMobileNumber(memberDto.getMobileNumber());
                existingDeletedMember.setAddress(memberDto.getAddress());
                memberRepo.save(existingDeletedMember);

                return ResponseDto.builder()
                        .status(true)
                        .message("Admin added successfully")
                        .build();
            }
            Member member = memberDtoConverter.dtoToEntity(memberDto);
            member.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));

            List<Role> role = roleRepo.getAdminRole();
            member.setRoles(role);

            member.setAccountState(AccountState.ACTIVE);

            memberRepo.save(member);
            return ResponseDto.builder()
                    .status(true)
                    .message("Admin registered successfully. Please log in to access your account.")
                    .build();
        }
        catch (Exception e){

            if(e.getMessage().contains("email")){
                return ResponseDto.builder()
                        .status(false)
                        .message("Admin already exists for given email address.")
                        .build();
            }
            if(e.getMessage().contains("username")){
                return ResponseDto.builder()
                        .status(false)
                        .message("Admin already exists for given username.")
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

    @Override
    public MemberDto viewMemberByUsername(String username){
        Member member = memberRepo.findMemberByUsername(username);
        MemberDto memberDto = memberDtoConverter.entityToDto(member);
        return  memberDto;
    }

    @Override
    public void resetPassword(String email) {
        Member member = memberRepo.findByEmail(email);
        String uniqueCode = this.passwordResetCode.generatePasswordResetCode();

        PasswordResetCode passwordResetCode = new PasswordResetCode();
        passwordResetCode.setUserId(member.getId());
        passwordResetCode.setResetCode(uniqueCode);
        passwordResetCodeRepo.save(passwordResetCode);

        emailSenderService.sendEmail(member.getEmail(),
                "This is your password reset link \n" +
                        "Please click the link below to reset your link \n" +
                        "localhost:8080/verify-rest-password/"+member.getId()+"/"+uniqueCode,
                "Reset Your Password");

    }

    public ResponseDto verifyResetLink(Integer id, String token){

        PasswordResetCode existingToken = passwordResetCodeRepo.findByResetCode(token);

        if(existingToken!=null){
            return ResponseDto.builder()
                    .message("Reset link verified")
                    .status(true)
                    .build();
        }

        return ResponseDto.builder()
                .message("Reset link is not correct. ")
                .status(false)
                .build();
    }

}
