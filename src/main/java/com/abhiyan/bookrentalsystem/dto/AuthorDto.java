package com.abhiyan.bookrentalsystem.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AuthorDto {

    private Integer id;

    @NotBlank(message = "Author Name is required.")
    private String name;

    @NotBlank(message = "Email is required.")
    @Email(message = "Enter a valid email address.")
    private String email;

//    @Length(min = 10, max = 10, message = "Phone number needs to be of 10 digits")
    @Pattern(regexp = "^[9]+[0-9]{9}$" ,message = "Phone number needs to be of 10 digits")
    private String mobileNumber;
}
