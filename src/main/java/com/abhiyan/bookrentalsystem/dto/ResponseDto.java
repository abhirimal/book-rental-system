package com.abhiyan.bookrentalsystem.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {

    private String message;

    private Boolean status;
}
