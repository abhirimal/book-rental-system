package com.abhiyan.bookrentalsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Integer id;

    @NotBlank(message = "Email is required.")
    @Email(message = "Valid email is required.")
    private String email;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Mobile Number is required")
    @Length(min = 10, max = 10, message = "Phone number needs to be of 10 digits")
    @Pattern(regexp = "^[9]+[0-9]{9}$" ,message = "Phone number needs to be of 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Address is required")
    private String address;
}
