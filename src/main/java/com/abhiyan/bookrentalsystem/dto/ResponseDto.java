package com.abhiyan.bookrentalsystem.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDto {

    private String message;

    private Boolean status;
}
