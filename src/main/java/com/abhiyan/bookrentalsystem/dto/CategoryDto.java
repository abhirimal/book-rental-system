package com.abhiyan.bookrentalsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDto {

    Integer id;

    @NotBlank(message = "Category is required.")
    String name;

    @NotBlank(message = "Description is required. ")
    String description;
}
