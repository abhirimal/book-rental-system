package com.abhiyan.bookrentalsystem.repository;

import com.abhiyan.bookrentalsystem.model.PasswordResetCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface PasswordResetCodeRepo extends JpaRepository<PasswordResetCode,Integer> {

    PasswordResetCode findByResetCode(String token);

    PasswordResetCode findByUserId(Integer id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "DELETE from reset_code where user_id=?1")
    void deletePasswordResetCodeByUserId(Integer id);


}
