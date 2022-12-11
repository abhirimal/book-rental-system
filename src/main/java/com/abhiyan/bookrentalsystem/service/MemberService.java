package com.abhiyan.bookrentalsystem.service;

import com.abhiyan.bookrentalsystem.dto.MemberDto;

import java.net.Inet4Address;
import java.util.List;

public interface MemberService {

    void saveMember(MemberDto memberDto);

    List<MemberDto> viewMembers();

    MemberDto editMember(Integer id);

    MemberDto updateMember(Integer id,MemberDto memberDto);

    void deleteMember(Integer id);

}
