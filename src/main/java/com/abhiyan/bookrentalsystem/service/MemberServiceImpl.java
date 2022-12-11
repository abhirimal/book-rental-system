package com.abhiyan.bookrentalsystem.service;

import com.abhiyan.bookrentalsystem.converter.MemberDtoConverter;
import com.abhiyan.bookrentalsystem.dto.MemberDto;
import com.abhiyan.bookrentalsystem.model.Member;
import com.abhiyan.bookrentalsystem.repository.MemberRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepo memberRepo;
    private final MemberDtoConverter memberDtoConverter;

    public MemberServiceImpl(MemberRepo memberRepo, MemberDtoConverter memberDtoConverter) {
        this.memberRepo = memberRepo;
        this.memberDtoConverter = memberDtoConverter;
    }

    @Override
    public void saveMember(MemberDto memberDto) {
        Member member = memberDtoConverter.dtoToEntity(memberDto);
        memberRepo.save(member);
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
