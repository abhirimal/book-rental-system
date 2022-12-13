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
    @Pattern(regexp = "[a-zA-Z0-9+_.-]+@[a-z]+[.][a-z]{3}", message = "Enter a valid email address. \n" +
            "Example: xyz@gmail.com")
    private String email;

    @NotBlank(message = "Mobile Number is required.")
    @Pattern(regexp = "^[9]+[0-9]{9}$" ,message = "Mobile number needs to be of 10 digits \n" +
            "Phone number should start with digit 9")
    private String mobileNumber;
}
