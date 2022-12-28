package com.abhiyan.bookrentalsystem.security;

import com.abhiyan.bookrentalsystem.dto.ResponseDto;
import com.abhiyan.bookrentalsystem.enums.AccountState;
import com.abhiyan.bookrentalsystem.model.Member;
import com.abhiyan.bookrentalsystem.repository.BookRepo;
import com.abhiyan.bookrentalsystem.repository.MemberRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepo memberRepo;
    private final BookRepo bookRepo;

    public CustomUserDetailsService(MemberRepo memberRepo, BookRepo bookRepo) {
        this.memberRepo = memberRepo;
        this.bookRepo = bookRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member userByUserName = memberRepo.findMemberByUsername(username);
        AccountState accountState = userByUserName.getAccountState();
        if(accountState==AccountState.DELETED){

                    throw new UsernameNotFoundException("User doesn't exist.");
        }

//        List<String> authorityList = userByUserName.getRoles().stream().map(role -> (role.getName())).collect(Collectors.toList());

//        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(String.valueOf(authorityList));

//        List<GrantedAuthority> grantedAuthorities = userByUserName.getRoles().stream().map(role -> (role.getName())).collect(Collectors.toList());
          List<GrantedAuthority> grantedAuthorities =
                  userByUserName.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().toString())).collect(Collectors.toList());
//        System.out.println(userByUserName.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));

        org.springframework.security.core.userdetails.User user =
                new org.springframework.security.core.userdetails.User(
                        userByUserName.getUsername(), userByUserName.getPassword(), grantedAuthorities);
        return user;
    }
}
