package com.abhiyan.bookrentalsystem.repository;

import com.abhiyan.bookrentalsystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role, Integer> {

    @Query(nativeQuery = true, value = "SELECT * from role where role_name=?1")
    List<Role> getAdminRole(String name);
}
