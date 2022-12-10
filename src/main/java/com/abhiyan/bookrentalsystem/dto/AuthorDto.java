package com.abhiyan.bookrentalsystem.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AuthorDto {

    private Integer id;

    private String name;

    private String email;

    private String mobile_number;
}
