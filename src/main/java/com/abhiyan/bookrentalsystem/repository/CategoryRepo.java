package com.abhiyan.bookrentalsystem.repository;

import com.abhiyan.bookrentalsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE category set account_state='DELETED' " +
            "where id=?1")
    void softDeleteCategoryById(int id);

    @Query(nativeQuery = true, value = "SELECT * from category where account_state='ACTIVE' ")
    List<Category> selectAllActiveCategory();

//    Category findByName(String name);

    @Query(nativeQuery = true, value = "SELECT * from category where name=?1 and " +
            "account_state='ACTIVE' ")
    Category findByNameAndActiveStatus(String name);

    @Query(nativeQuery = true, value = "SELECT * from category where name=?1 and " +
            "account_state='DELETED' ")
    Category findDeletedStateCategory(String name);
}
