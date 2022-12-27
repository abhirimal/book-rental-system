package com.abhiyan.bookrentalsystem.dto;

import com.abhiyan.bookrentalsystem.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Integer id;

    @NotBlank(message = "Email is required.")
    @Pattern(regexp = "[a-zA-Z0-9+_.-]+@[a-z]+[.][a-z]{3}", message = "Enter a valid email address. \n" +
            "Example: xyz@gmail.com")
    private String email;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Mobile Number is required")
    @Pattern(regexp = "^[9]+[0-9]{9}$" ,message = "Mobile number needs to be of 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Password is required")
    @Pattern(regexp ="^[a-zA-z0-9]{8,}$",message = "Password should be of 8 digits")
    private String password;

    @Pattern(regexp ="^[a-zA-z0-9]{6,}$",message = "Username should be of 6 digits")
    private String username;

    private List<Role> roles;
}
