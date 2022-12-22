package com.abhiyan.bookrentalsystem.repository;

import com.abhiyan.bookrentalsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE category set account_state='DELETED' " +
            "where id=?1")
    void softDeleteCategoryById(int id);

    @Query(nativeQuery = true, value = "SELECT * from category where account_state='ACTIVE' ")
    List<Category> selectAllActiveCategory();

}
