package com.abhiyan.bookrentalsystem.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class RentDto {

    @NotEmpty(message = "Select atleast one author")
    List<Integer> codeList;


}
