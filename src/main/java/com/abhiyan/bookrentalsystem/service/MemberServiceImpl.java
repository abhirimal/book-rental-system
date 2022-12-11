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
        return null;
    }

    @Override
    public MemberDto updateMember(Integer id, MemberDto memberDto) {
        return null;
    }

    @Override
    public void deleteMember(Integer id) {
    memberRepo.deleteById(id);
    }
}
