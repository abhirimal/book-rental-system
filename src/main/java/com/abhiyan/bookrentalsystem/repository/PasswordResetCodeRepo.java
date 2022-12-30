package com.abhiyan.bookrentalsystem.repository;

import com.abhiyan.bookrentalsystem.model.PasswordResetCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetCodeRepo extends JpaRepository<PasswordResetCode,Integer> {

    PasswordResetCode findByResetCode(String token);
}
