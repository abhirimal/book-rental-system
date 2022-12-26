package com.abhiyan.bookrentalsystem.repository;

import com.abhiyan.bookrentalsystem.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface MemberRepo extends JpaRepository <Member,Integer> {

    @Query(nativeQuery = true, value = "SELECT * from member where account_state='ACTIVE'")
    List<Member> findAllActiveMember();


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE member set account_state='DELETED'" +
            "where id=?1")
    void softDeleteMemberById(int id);

    @Query(nativeQuery = true,value = "SELECT * from member where " +
            "account_state='DELETED' and email=?1")
    Member findDeletedStateMember(String email);

    @Query(nativeQuery = true, value = "select *\n" +
            "from member m\n" +
            "where account_state='ACTIVE' and lower(m.name) like concat(lower(?1), '%')")
    List<Member> findAllByName(String memberName);

    @Query(nativeQuery = true, value = " SELECT * from member where email='?1'")
    Member findMemberByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT * from member where username=?1")
    Member findMemberByUsername(String username);


}
