package com.abhiyan.bookrentalsystem.converter;

import com.abhiyan.bookrentalsystem.dto.MemberDto;
import com.abhiyan.bookrentalsystem.model.Member;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberDtoConverter {

    public MemberDto entityToDto(Member member){
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setName(member.getName());
        memberDto.setEmail(member.getEmail());
        memberDto.setAddress(member.getAddress());
        memberDto.setMobileNumber(member.getMobileNumber());
        memberDto.setPassword(member.getPassword());
//        memberDto.setRoles(member.getRoles());
        memberDto.setUsername(member.getUsername());
        return memberDto;
    }

    public Member dtoToEntity(MemberDto memberDto){
        Member member = new Member();
        member.setName(memberDto.getName());
        member.setEmail(memberDto.getEmail());
        member.setAddress(memberDto.getAddress());
        member.setMobileNumber(memberDto.getMobileNumber());
        member.setUsername(memberDto.getUsername());
//        member.setPassword(memberDto.getPassword());
        return member;
    }

    public List<MemberDto> entityToDto(List<Member> members){
        return members.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }
}
