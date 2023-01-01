package com.abhiyan.bookrentalsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewPasswordRequestDto {

    @NotBlank(message = "Password is required")
    @Pattern(regexp ="^[a-zA-z0-9]{8,}$",message = "Password should be of 8 digits")
    private String password;
}
