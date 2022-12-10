package com.abhiyan.bookrentalsystem.repository;

import com.abhiyan.bookrentalsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
