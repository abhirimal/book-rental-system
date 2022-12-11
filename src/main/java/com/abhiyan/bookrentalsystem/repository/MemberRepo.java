package com.abhiyan.bookrentalsystem.repository;

import com.abhiyan.bookrentalsystem.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository <Member,Integer> {
}
