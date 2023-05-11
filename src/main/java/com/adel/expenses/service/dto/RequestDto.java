package com.adel.expenses.service.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {

    private String name;
    private Integer year;
    private  Boolean active;


}
